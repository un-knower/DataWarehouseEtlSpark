package cn.whaley.datawarehouse.dimension.moretv

import cn.whaley.datawarehouse.dimension.DimensionBase
import cn.whaley.datawarehouse.dimension.constant.SourceType._
import cn.whaley.datawarehouse.util.MysqlDB

/**
  * Created by Chubby on 3/13/17.
  * 节假日信息
  */
object HolidayDetail extends DimensionBase {

  dimensionName = "dim_holiday_detail"

  columns.skName = "day_sk"

  columns.primaryKeys = List("dim_day_id")

  columns.allColumns = List(
    "dim_day_id", "dim_day_info", "dim_name", "dim_type_infp"
  )


  readSourceType = jdbc

  sourceColumnMap = Map(
    columns.primaryKeys(0) -> "id",
    columns.allColumns(1) -> "day",
    columns.allColumns(2) -> "name",
    columns.allColumns(3) -> "type"
  )

  sourceDb = MysqlDB.dwDimensionDb("holiday_detail")

  sourceFilterWhere = "day is not null and day <> ''"


}
