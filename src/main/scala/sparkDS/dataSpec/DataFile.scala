/*
 * Copyright 2021 by DJ Chen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package sparkDS.dataSpec

import org.apache.spark.sql.DataFrame
import org.apache.spark.sql.functions.expr
import org.apache.spark.sql.types._
import sparkDS.dataSpec.columnType.ColumnBase
import sparkDS.dataValidation._

import scala.collection.mutable

abstract class DataFile
(
  val name: String,
  val columns: List[ColumnBase]
) {
  val schema: StructType = StructType(columns.map(col => col.structField))

  /**
   * Add column logic_validation_messages to the input data frame.
   * Client code then can get the validation result by selecting row with non-empty logic_validation_messages.
   *
   * @param dataframe
   */
  def validate(dataframe: DataFrame): Unit = {
    dataframe.withColumn("logic_validation_result", expr(s"filter(${sqlAssertionCode()}, msg -> isnotnull(msg)"))
  }

  def addRecordValidator(validator: RecordValidator): mutable.Seq[RecordValidator] = {
    _sqlCodeUpdated = false
    _validators :+ validator
  }

  override def toString: String = name

  /**
   * Generate SQL code for constructing the array of validation result struct by:
   * 1. Call column validators
   * 2. Call record validators
   *
   * @return Array of validation result struct
   */
  private def sqlAssertionCode(): String = {
    if (!_sqlCodeUpdated) {
      // generate SQL Code for accumulate validation message array.

      val column_validation_arrays = columns.foldLeft(
        s"array_union(array(null)"
      )(
        (combinedCode, col) => s"$combinedCode, ${col.sqlAssertionCode()}"
      ) + ")"

      var record_validation_array = _validators.foldLeft(
        "array(null"
      )(
        (combinedCode, vd) => s"$combinedCode, if(${vd.sqlAssertionCode}, struct('RECORD', ${vd.name}, $name, ${vd.assertionMessage}), null)"
      ) + ")"

      _sqlCode = s"array_union($column_validation_arrays, $record_validation_array)"
      _sqlCodeUpdated = true
    }
    _sqlCode
  }

  private val _validators = mutable.Seq[RecordValidator]()
  private var _sqlCodeUpdated = false
  private var _sqlCode: String = _
}