package org.wolflink.etherion.lib.enums.bazi

import org.wolflink.etherion.lib.enums.base.*

enum class ShiShens(val chineseName: String) {
    ZhengGuan("正官"),
    QiSha("七杀"), ZhengYin("正印"), PianYin("偏印"), ShiShen("食神"),
    ShangGuan("伤官"), BiJian("比肩"), JieCai("劫财"), ZhengCai("正财"), PianCai("偏财");

    companion object {
        fun get(master: TianGan, another: TianGan): ShiShens {
            val yinYangRelation = YinYangRelation.get(master.yinYang, another.yinYang)
            val pairShiShens = get(master.wuXing,another.wuXing)
            return if(yinYangRelation == YinYangRelation.TongXing) pairShiShens.first
            else pairShiShens.second
        }
        fun get(master: WuXing, another: WuXing): Pair<ShiShens,ShiShens> {
            val wuXingRelation = master relationTo another
            return when (wuXingRelation) {
                WuXingRelation.ShengWo -> PianYin to ZhengYin
                WuXingRelation.WoSheng -> ShiShen to ShangGuan
                WuXingRelation.KeWo ->  QiSha to ZhengGuan
                WuXingRelation.WoKe -> PianCai to ZhengCai
                WuXingRelation.TongWo -> BiJian to JieCai
            }
        }
    }
}

/**
 * 将五种固定的十神组合转为常用词语：
 * 食伤，财才，官杀，印枭，比劫
 */
fun Pair<ShiShens,ShiShens>.string(): String {
    return when {
        (first == ShiShens.BiJian && second == ShiShens.JieCai) -> "比劫"
        (first == ShiShens.PianCai && second == ShiShens.ZhengCai) -> "财才"
        (first == ShiShens.PianYin && second == ShiShens.ZhengYin) -> "印枭"
        (first == ShiShens.QiSha && second == ShiShens.ZhengGuan) -> "官杀"
        (first == ShiShens.ShiShen && second == ShiShens.ShangGuan) -> "食伤"
        else -> "未知组合：$this"
    }
}