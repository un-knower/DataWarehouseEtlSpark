package cn.whaley.datawarehouse.fact.util

import org.junit.Assert._
import org.junit.{Before, Test}

/**
  * Created by wujiulin on 2017/5/5.
  */
class ListCategoryUtilsTest {
  var MORETV: String = _
  var MEDUSA: String = _

  @Before
  def initialize() {
    MORETV = "moretv"
    MEDUSA = "medusa"
  }

  @Test
  def getListCategory: Unit = {
    var path_message: String = null
    val moretvTestCaseList = List(//pathMain, path, flag, mainCategory, secondCategory, thirdCategory
      //comic
      ("", "home-comic-hot_comic_top-similar", MORETV, "comic", "hot_comic_top", null),
      ("", "home-comic-comic_zhujue-comic259", MORETV, "comic", "comic_zhujue", null),
      ("", "home-comic-comic_zhuanti-comic134", MORETV, "comic", "comic_zhuanti", null),
      ("", "home-comic-dongman_xinfan-comic203-similar", MORETV, "comic", "dongman_xinfan", null),
      ("", "home-comic-comic_dashi-comic158", MORETV, "comic", "comic_dashi", null),
      //tv
      ("", "home-tv-tv_kangzhanfengyun", MORETV, "tv", "tv_kangzhanfengyun", null),
      ("", "home-tv-tv_zhuanti-tv235", MORETV, "tv", "tv_zhuanti", null),
      ("", "thirdparty_0-tv-tv_meizhouyixing-tv333", MORETV, "tv", "tv_meizhouyixing", null),
      ("", "thirdparty_1-tv-tv_genbo", MORETV, "tv", "tv_genbo", null),
      //zongyi
      ("", "home-zongyi-zongyi_weishi-zongyi3-similar", MORETV, "zongyi", "zongyi_weishi", null),
      ("", "home-zongyi-zongyi_zhuanti-zongyi71", MORETV, "zongyi", "zongyi_zhuanti", null),
      ("", "home-zongyi-p_zongyi_hot_1-zongyi248", MORETV, "zongyi", "p_zongyi_hot_1", null),
      //jilu
      ("", "home-jilu-jilu_meishi", MORETV, "jilu", "jilu_meishi", null),
      ("", "home-jilu-jishi_zhuanti-jilu20", MORETV, "jilu", "jishi_zhuanti", null),
      ("", "home-jilu-1_jilu_tags_shehui-similar", MORETV, "jilu", "1_jilu_tags_shehui", null),
      ("", "home-jilu-1_jilu_station_bbc", MORETV, "jilu", "1_jilu_station_bbc", null),
      ("", "home-jilu-1_jilu_tags_lishi-similar", MORETV, "jilu", "1_jilu_tags_lishi", null),
      ("", "thirdparty_0-jilu-p_document_1-jilu25", MORETV, "jilu", "p_document_1", null),
      ("", "home-jilu-jishi_wangpai", MORETV, "jilu", "jishi_wangpai", null),
      ("", "home-jilu-1_jilu_tags_renwu", MORETV, "jilu", "1_jilu_tags_renwu", null),
      //hot
      ("", "home-hot-hot_zhuanti-hot399", MORETV, "hot", "hot_zhuanti", null),
      ("", "thirdparty_0-hot-1_hot_tag_qingsonggaoxiao", MORETV, "hot", "1_hot_tag_qingsonggaoxiao", null),
      //movie
      ("", "home-movie-movie_zhuanti-tv115", MORETV, "movie", "movie_zhuanti", null),
      ("", "home-movie-movie_star-movie817", MORETV, "movie", "movie_star", null),
      ("", "home-movie-movie_teseyingyuan-movie405-movie793-peoplealsolike", MORETV, "movie", "movie_teseyingyuan", null),
      ("", "home-movie-movie_jujiaodian-movie975-peoplealsolike", MORETV, "movie", "movie_jujiaodian", null),
      ("", "thirdparty_0-movie-movie_xilie-movie907", MORETV, "movie", "movie_xilie", null),
      //xiqu
      ("", "thirdparty_0-xiqu-1_xiqu_tags_chaoju7", MORETV, "xiqu", "1_xiqu_tags_chaoju7", null),
      ("", "home-xiqu-1_xiqu_tags_jinju", MORETV, "xiqu", "1_xiqu_tags_jinju", null),
      ("", "thirdparty_0-xiqu-1_tv_xiqu_tag_yuju", MORETV, "xiqu", "1_tv_xiqu_tag_yuju", null)
      //kids
    )

    val medusaTestCaseList = List(//pathMain, path, flag, mainCategory, secondCategory, thirdCategory
      //kids
      ("home*classification*kids-kids_home-tingerge-search*XMF", "", MEDUSA, "kids", "show_kidsSongSite", "搜一搜"),
      ("home*classification*kids-kids_home-kandonghua-search*WJ", "", MEDUSA, "kids", "show_kidsSite", "搜一搜"),
      ("home*classification*kids-kids_home-xuezhishi-search*DJA", "", MEDUSA, "kids", "kids_knowledge", "搜一搜"),
      //sports
      ("home*my_tv*4-sports*League*qipai-league*围棋", "", MEDUSA, "sports", "leagueEntry", "qipai"),
      ("home*my_tv*7-sports*League*dj", "", MEDUSA, "sports", "leagueEntry", "dj"),
      //mv
      ("home*classification*mv-mv*function*site_mvsubject-mv_poster", "", MEDUSA, "mv", "site_mvsubject", ""),
      ("home*my_tv*mv-mv*mvCategoryHomePage*site_mvarea-mv_category*欧美", "", MEDUSA, "mv", "site_mvarea", "欧美"),
      ("home*classification*mv-mv*mvCategoryHomePage*site_mvstyle-mv_category*朋克", "", MEDUSA, "mv", "site_mvstyle", "朋克"),
      ("home*my_tv*mv-mv*function*site_concert-mv_category*日本", "", MEDUSA, "mv", "site_concert", "日本"),
      ("home*classification*mv-mv*function*site_dance-mv_category*三次元舞蹈", "", MEDUSA, "mv", "site_dance", "三次元舞蹈"),
      ("home*my_tv*mv-mv*mvTopHomePage*site_mvtop-mv_poster", "", MEDUSA, "mv", "site_mvtop", ""),
      ("home*recommendation*14-mv*function*site_hotsinger-mv_poster", "", MEDUSA, "mv", "site_hotsinger", ""),
      ("home*my_tv*mv-mv*mvCategoryHomePage*site_mvyear-mv_category*80年代", "", MEDUSA, "mv", "site_mvyear", "80年代"),
      //comic
      ("home*classification*comic-comic-retrieval*hot*maoxian*neidi*2015", "", MEDUSA, "comic", "筛选", null),
      ("home*classification*comic-comic-search*QHD", "", MEDUSA, "comic", "搜索", null),
      ("home*my_tv*comic-comic*国语动画", "", MEDUSA, "comic", "国语动画", null),
      //tv
      ("home*classification*tv-tv-search*DCG", "", MEDUSA, "tv", "搜索", null),
      ("home*my_tv*tv-tv-retrieval*new*all*neidi*all", "", MEDUSA, "tv", "筛选", null),
      ("home*my_tv*movie-tv*特色美剧", "", MEDUSA, "tv", "特色美剧", null),
      //zongyi
      ("home*my_tv*zongyi-zongyi-search*AB", "", MEDUSA, "zongyi", "搜索", null),
      ("home*my_tv*zongyi-zongyi-retrieval*hot*gaoxiao*xianggang*all", "", MEDUSA, "zongyi", "筛选", null),
      ("home*my_tv*zongyi-zongyi*情感访谈", "", MEDUSA, "zongyi", "情感访谈", null),
      ("home*my_tv*zongyi-zongyi*港台精选", "", MEDUSA, "zongyi", "港台精选", null),
      ("home*classification*zongyi*相声小品", "", MEDUSA, "zongyi", "相声小品", null),
      //jilu
      ("home*classification*jilu-jilu*自然万象", "", MEDUSA, "jilu", "自然万象", null),
      ("home*classification*jilu-jilu-search*WZRY", "", MEDUSA, "jilu", "搜索", null),
      ("home*classification*jilu-jilu-retrieval*hot*qita*cctv*all", "", MEDUSA, "jilu", "筛选", null),
      ("home*classification*jilu-jilu-retrieval*hot*qita*cctv*all", "", MEDUSA, "jilu", "筛选", null),
      ("home*classification*jilu-jilu*刑侦档案", "", MEDUSA, "jilu", "刑侦档案", null),
      ("home*classification*jilu-jilu*公开课", "", MEDUSA, "jilu", "公开课", null),
      ("home*classification*jilu-jilu*铁血军魂", "", MEDUSA, "jilu", "铁血军魂", null),
      ("home*my_tv*jilu-jilu*NHK", "", MEDUSA, "jilu", "NHK", null),
      ("home*my_tv*jilu-jilu*前沿科技", "", MEDUSA, "jilu", "前沿科技", null),
      ("home*classification*jilu-jilu*VICE专区", "", MEDUSA, "jilu", "VICE专区", null),
      ("home*classification*jilu-jilu*其他分类", "", MEDUSA, "jilu", "其他分类", null),
      //hot
      ("home*classification*hot-hot-search*SIHUOS", "", MEDUSA, "hot", "搜索", null),
      ("home*recommendation*1-hot*五花八门", "", MEDUSA, "hot", "五花八门", null),
      ("home*classification*hot-hot*游戏动画", "", MEDUSA, "hot", "游戏动画", null),
      ("home*recommendation*1-hot*今日焦点", "", MEDUSA, "hot", "今日焦点", null),
      ("home*classification*hot-hot*弹幕专区", "", MEDUSA, "hot", "弹幕专区", null),
      ("home*my_tv*hot-hot*VICE专区", "", MEDUSA, "hot", "VICE专区", null),
      ("home*classification*hot-hot*短视频专题", "", MEDUSA, "hot", "短视频专题", null),
      ("home*my_tv*hot-hot*影视短片*电台", "", MEDUSA, "hot", "影视短片", null),
      ("home*recommendation*1-hot*创意运动", "", MEDUSA, "hot", "创意运动", null),
      ("home*classification*hot-hot*音乐舞蹈*电台", "", MEDUSA, "hot", "音乐舞蹈", null),
      //movie
      ("home*classification*movie-movie-search*DLZ", "", MEDUSA, "movie", "搜索", null),
      ("home*my_tv*movie-movie-retrieval*hot*xiju*neidi*2010", "", MEDUSA, "movie", "筛选", null),
      ("home*my_tv*movie-movie*午夜场", "", MEDUSA, "movie", "午夜场", null),
      //xiqu
      ("home*classification*xiqu-xiqu-search*JINGJU", "", MEDUSA, "xiqu", "搜索", null),
      ("home*classification*xiqu-xiqu*广场舞*电台", "", MEDUSA, "xiqu", "广场舞", null),
      ("home*classification*xiqu-xiqu*黄梅戏*电台", "", MEDUSA, "xiqu", "黄梅戏", null),
      ("home*classification*xiqu-xiqu*沪剧", "", MEDUSA, "xiqu", "沪剧", null),
      ("home*my_tv*xiqu-xiqu*二人转", "", MEDUSA, "xiqu", "二人转", null),
      ("home*classification*xiqu-xiqu*锡剧*电台", "", MEDUSA, "xiqu", "锡剧", null),
      ("home*live*eagle-xiqu*京剧", "", MEDUSA, "xiqu", "京剧", null),
      ("home*classification*xiqu-xiqu*粤剧", "", MEDUSA, "xiqu", "粤剧", null),
      ("home*classification*xiqu-xiqu*歌仔戏", "", MEDUSA, "xiqu", "歌仔戏", null),
      ("home*my_tv*xiqu-xiqu*苏州弹唱", "", MEDUSA, "xiqu", "苏州弹唱", null),
      ("home*classification*xiqu-xiqu*越剧", "", MEDUSA, "xiqu", "越剧", null),
      ("home*my_tv*xiqu-xiqu*吕剧", "", MEDUSA, "xiqu", "吕剧", null)
    )

    val testCaseList = moretvTestCaseList ++ medusaTestCaseList
    testCaseList.foreach(f = w => {
      if (w._1 == "") path_message = w._2 else path_message = w._1
      val main_category = ListCategoryUtils.getListMainCategory(w._1, w._2, w._3)
      val second_category = ListCategoryUtils.getListSecondCategory(w._1, w._2, w._3)
      val third_category = ListCategoryUtils.getListThirdCategory(w._1, w._2, w._3)
      try {
        assertEquals(w._4, main_category)
        assertEquals(w._5, second_category)
        assertEquals(w._6, third_category)
      } catch {
        case e: AssertionError =>
          println("fail test case: " + path_message)
          throw e
      }

    })
  }

}