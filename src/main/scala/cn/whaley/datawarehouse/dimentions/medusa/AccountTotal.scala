package cn.whaley.datawarehouse.dimentions.medusa

import cn.whaley.datawarehouse.BaseClass
import cn.whaley.datawarehouse.util.{HdfsUtil, MysqlDB}

/**
  * Created by Tony on 16/12/22.
  *
  * 电视猫账号信息全量更新
  */
object AccountTotal extends BaseClass {
  override def execute(args: Array[String]): Unit = {


    val jdbcDF = sqlContext.read.format("jdbc").options(MysqlDB.medusaUCenterMember).load()
    jdbcDF.registerTempTable("bbs_ucenter_members")

    val df = sqlContext.sql("SELECT cast(moretvid as int) as account_sk, moretvid as account_id, " +
      "username as user_name, email, mobile, " +
      "from_unixtime(regdate,'yyyy-MM-dd HH:mm:ss') as reg_time, " +
      "registerfrom as register_from " +
      " from bbs_ucenter_members")

    HdfsUtil.deleteHDFSFileOrPath("/data_warehouse/dimensions/medusa/account")
    df.write.parquet("/data_warehouse/dimensions/medusa/account")
  }

}
