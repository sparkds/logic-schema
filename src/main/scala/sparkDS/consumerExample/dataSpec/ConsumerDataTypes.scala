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

package sparkDS.consumerExample.dataSpec

import org.apache.spark.sql.types.{DataTypes, DecimalType, StructField, StructType}
import sparkDS.consumerExample.dataSpec.columns.CommonColumns

object ConsumerDataTypes {
  val PriceAmount: DecimalType = DataTypes.createDecimalType(7,2)
  val SalesAmount: DecimalType = DataTypes.createDecimalType(9,2)

  val ProductPropertiesStruct: StructType = DataTypes.createStructType(
    List[StructField](
      CommonColumns.product_id.structField,
      CommonColumns.product_name.structField,
      CommonColumns.listing_product_price.structField
    ).toArray)
}
