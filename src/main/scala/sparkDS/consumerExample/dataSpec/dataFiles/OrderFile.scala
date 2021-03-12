package sparkDS.consumerExample.dataSpec.dataFiles

import sparkDS.consumerExample.dataSpec.columns.CommonColumns._
import sparkDS.dataSpec.DataFile
import sparkDS.dataSpec.columnType.ColumnBase

object OrderFile extends DataFile(
  "OrderFile",
  List[ColumnBase](
    customer_id,
    product_id,
    sale_timestamp,
    sale_product_price
  )
)