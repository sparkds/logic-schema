package sparkDS.consumerExample.dataSpec.dataFiles

import sparkDS.consumerExample.dataSpec.columns.CommonColumns._
import sparkDS.dataSpec.DataFile
import sparkDS.dataSpec.columnType.ColumnBase

object CustoerFile extends DataFile(
  "OrderFile",
  List[ColumnBase](
    customer_id,
    customer_name,
    date_of_birth,
    street_address,
    city_name,
    postal_code,
    postal_state,
    joined_date
  )
)