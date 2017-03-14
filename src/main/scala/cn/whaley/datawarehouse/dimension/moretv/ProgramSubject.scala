package cn.whaley.datawarehouse.dimension.moretv

import cn.whaley.datawarehouse.dimension.DimensionBase
import cn.whaley.datawarehouse.dimension.constant.SourceType._
import cn.whaley.datawarehouse.util.MysqlDB
import org.apache.spark.sql.DataFrame


/**
  * Created by witnes on 3/13/17.
  *
  * 电视猫_节目专题维度表
  */
object ProgramSubject extends DimensionBase {

  dimensionName = "dim_medusa_subject"

  columns.skName = "subject_sk"

  columns.primaryKeys = List("subject_code")

  columns.trackingColumns = List()

  columns.otherColumns = List(

    "subject_name", "subject_title", "subject_content_type",
    "subject_content_type_name", "subject_create_time", "subject_publish_time"

  )

  readSourceType = jdbc

  sourceDb = MysqlDB.medusaCms("mtv_subject", "ID", 1, 4000, 5)



  /**
    * 处理原数据的自定义的方法
    * 默认可以通过配置实现，如果需要自定义处理逻辑，可以再在子类中重载实现
    *
    * @param sourceDf
    * @return
    */
  override def filterSource(sourceDf: DataFrame): DataFrame = {

    val sq = sqlContext
    import sq.implicits._
    import org.apache.spark.sql.functions._

    val contentTypeDb = MysqlDB.medusaCms("mtv_content_type", "id", 1, 100, 1)

    val contentTypeDf = sqlContext.read.format("jdbc").options(contentTypeDb).load()
      .select($"code", $"name")

    sourceDf.filter($"code".isNotNull && $"code" != "").as("s")
      .withColumn("code", regexp_extract($"code", "[a-z]*", 0))

      .join(contentTypeDf.as("c"), "code" :: Nil, "inner")

      .select(
        $"s.code".as(columns.primaryKeys(0)),
        $"s.name".as(columns.otherColumns(0)),
        $"s.title".as(columns.otherColumns(1)),
        $"c.code".as(columns.otherColumns(2)),
        $"c.name".as(columns.otherColumns(3)),
        unix_timestamp($"s.create_time").as(columns.otherColumns(3)),
        unix_timestamp($"s.publish_time").as(columns.otherColumns(4))
      )

  }
}
