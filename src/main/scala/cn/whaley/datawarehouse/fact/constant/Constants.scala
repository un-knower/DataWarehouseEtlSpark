package cn.whaley.datawarehouse.fact.constant

import cn.whaley.datawarehouse.global.Globals._

/**
  * Created by michael on 17/5/5.
  */
object Constants {
  val FACT_HDFS_BASE_PATH_BACKUP: String = FACT_HDFS_BASE_PATH + "/backup"
  val FACT_HDFS_BASE_PATH_TMP: String = FACT_HDFS_BASE_PATH + "/tmp"
  val FACT_HDFS_BASE_PATH_DELETE: String = FACT_HDFS_BASE_PATH + "/delete"
  val THRESHOLD_VALUE = 512000
}
