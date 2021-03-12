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

package sparkDS.dataSpec.columnType

import org.apache.spark.sql.types._
import sparkDS.dataValidation.ColumnValidator

import scala.collection.mutable

class ColumnBase
(
  val name: String,
  val dt: DataType
) {
  val structField: StructField = DataTypes.createStructField(name, dt, true)

  def addValidator(validator: ColumnValidator): mutable.Seq[ColumnValidator] = {
    _sqlCodeUpdated = false
    _validators :+ ColumnValidator(validator.name,
      validator.sqlAssertionCode.replace("%this_col%", name),
      validator.assertionMessage.replace("%this_col%", name))
  }

  /**
   * Combine the sqlAssertionCode of individual column validator, replacing the %this_col% with the column name.
   *
   * @return
   */
  def sqlAssertionCode(): String = {
    if (!_sqlCodeUpdated) {
      // generate SQL Code for accumulate validation message array.
      _sqlCode = _validators.foldLeft(
        "array(null"
      )(
        (combinedCode, vd) => s"$combinedCode, if(${vd.sqlAssertionCode}, struct('COLUMN', ${vd.name}, $name, ${vd.assertionMessage}), null)"
      ) + ")"
      _sqlCodeUpdated = true
    }
    _sqlCode
  }

  override def toString: String = name

  // Private members
  private val _validators: mutable.Seq[ColumnValidator] = mutable.Seq[ColumnValidator]()
  private var _sqlCodeUpdated: Boolean = false
  private var _sqlCode: String = _
}
