package cn.whaley.datawarehouse.fact.util

import cn.whaley.datawarehouse.global.LogConfig

/**
  * Created by michael on 2017/5/2.
  */
object RecommendUtils extends LogConfig {
  private val medusaReg = ("(similar|peoplealsolike|guessyoulike)-[\\S]+-([\\S]+)\\*([\\S]+)").r
  private val moretvReg = ("home.*-(similar|peoplealsolike|guessyoulike)").r

  def getRecommendSourceType(pathSub: String, path: String, flag: String): String = {
    var result: String = null
    flag match {
      case MEDUSA => {
        if (null != pathSub) {
          medusaReg findFirstMatchIn pathSub match {
            case Some(p) => {
              result = p.group(1)
            }
            case None =>
          }
        }
      }
      case MORETV => {
        if (null != path) {
          moretvReg findFirstMatchIn path match {
            case Some(p) => {
              result = p.group(1)
            }
            case None =>
          }
        }
      }
    }
    result
  }

  def getPreviousSid(pathSub: String): String = {
    var result: String = null
    if (null != pathSub) {
      medusaReg findFirstMatchIn pathSub match {
        case Some(p) => {
          result = p.group(2)
        }
        case None =>
      }
    }
    result
  }

  def getPreviousContentType(pathSub: String): String = {
    var result: String = null
    if (null != pathSub) {
      medusaReg findFirstMatchIn pathSub match {
        case Some(p) => {
          result = p.group(3)
        }
        case None =>
      }
    }
    result
  }

  /* 推荐维度表，获得sk
 recommendSourceType
 previousContentType
 recommendType

 home*recommendation*14

 再看一下path的recommendation

 Container killed by YARN for exceeding memory limits. 11.5 GB of 11 GB physical memory used. Consider boosting spark.yarn.executor.memoryOverhead
 */

}
