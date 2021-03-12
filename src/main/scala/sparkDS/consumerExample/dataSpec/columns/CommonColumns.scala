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

package sparkDS.consumerExample.dataSpec.columns

import sparkDS.consumerExample.dataSpec.columnType.{PriceAmountColumn, SalesAmountColumn}
import sparkDS.dataSpec.columnType.{DateColumn, LongColumn, StringColumn, TimestampColumn}

object CommonColumns {
  //@formatter:off
  val customer_id                                         = new LongColumn("customer_id")
  val customer_name                                       = new StringColumn("customer_name")
  val date_of_birth                                       = new DateColumn("date_of_birth")
  val joined_date                                         = new DateColumn("joined_date")
  val street_address                                      = new StringColumn("street_address")
  val city_name                                           = new StringColumn("city_name")
  val postal_code                                         = new StringColumn("postal_code")
  val postal_state                                        = new StringColumn("postal_state")

  val product_id                                          = new LongColumn("product_id")
  val product_name                                        = new StringColumn("product_name")
  val listing_product_price                               = new PriceAmountColumn("listing_product_price")
  val sale_product_price                                  = new PriceAmountColumn("sale_product_price")
  val sale_timestamp                                      = new TimestampColumn("sale_timestamp")
  val sales_month                                         = new SalesAmountColumn("sales_month")
  val sales_year                                          = new SalesAmountColumn("sales_year")
  //@formatter:on
}