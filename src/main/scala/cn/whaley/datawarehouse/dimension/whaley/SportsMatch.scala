package cn.whaley.datawarehouse.dimension.whaley

import cn.whaley.datawarehouse.dimension.DimensionBase
import cn.whaley.datawarehouse.dimension.constant.SourceType._
import cn.whaley.datawarehouse.util.MysqlDB


/**
  * Created by czw on 17/3/14.
  *
  * 微鲸端体育比赛维度表
  */
object SportsMatch extends DimensionBase {
  columns.skName = "match_sk"
  columns.primaryKeys = List("match_sid")
  columns.trackingColumns = List()
  columns.otherColumns = List("match_name","match_category","match_date","match_source","league_id")

  readSourceType = jdbc

  //维度表的字段对应源数据的获取方式
  sourceColumnMap = Map(

  )

  sourceFilterWhere = "match_sid is not null and match_sid <> ''"
  sourceDb = MysqlDB.medusaUCenterMember

  dimensionName = "dim_whaley_mv_sports_match"
}