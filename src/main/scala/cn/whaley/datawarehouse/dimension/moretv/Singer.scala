package cn.whaley.datawarehouse.dimension.moretv

import cn.whaley.datawarehouse.dimension.DimensionBase
import cn.whaley.datawarehouse.dimension.constant.SourceType._
import cn.whaley.datawarehouse.util.MysqlDB

/**
  * Created by witnes on 3/13/17.
  * 歌手维度表
  */
object Singer extends DimensionBase {

  dimensionName = "dim_medusa_singer"

  columns.skName = "singer_sk"

  columns.primaryKeys = List("singer_id")

  columns.trackingColumns = List()

  columns.otherColumns = List("singer_name", "singer_area", "singer_birthday", "singer_create_time", "singer_publish_time")


  readSourceType = jdbc

  sourceDb = MysqlDB.medusaCms("mtv_singer", "id", 1, 134, 1)

  sourceColumnMap = Map(
    columns.primaryKeys(0) -> "sid",
    columns.otherColumns(0) -> "name",
    columns.otherColumns(1) -> "area",
    columns.otherColumns(2) -> "birthday",
    columns.otherColumns(3) -> "create_time",
    columns.otherColumns(4) -> "publish_time"
  )

  sourceDb = MysqlDB.medusaCms("mtv_mvtopic", "id", 1, 550, 1)

  sourceFilterWhere = "sid is not null and sid <> ''"

}
