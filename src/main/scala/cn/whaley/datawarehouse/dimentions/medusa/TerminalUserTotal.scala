package cn.whaley.datawarehouse.dimentions.medusa

import cn.whaley.datawarehouse.BaseClass
import cn.whaley.datawarehouse.util.{HdfsUtil, MysqlDB}

/**
  * Created by Tony on 16/12/22.
  *
  * 电视猫终端用户信息全量更新
  */
object TerminalUserTotal extends BaseClass {
  override def execute(args: Array[String]): Unit = {

    val jdbcDF = sqlContext.read.format("jdbc").options(MysqlDB.medusaTvServiceAccount).load()
    jdbcDF.registerTempTable("mtv_account")

    val df = sqlContext.sql("SELECT id as terminal_sk, user_id, openTime as open_time, mac, wifi_mac, " +
      "product_model, product_serial, promotion_channel, lastLoginTime as last_login_time " +
      " from mtv_account")

    HdfsUtil.deleteHDFSFileOrPath("/data_warehouse/dimensions/medusa/terminal_user")
    df.write.parquet("/data_warehouse/dimensions/medusa/terminal_user")
  }
}
