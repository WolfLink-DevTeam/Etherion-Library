package org.wolflink.etherion.api.enums.bazi

import org.wolflink.etherion.api.enums.base.TianGan

enum class ShiShens(val chineseName: String) {
    ZhengGuan("正官"),
    QiSha("七杀"), ZhengYin("正印"), PianYin("偏印"), ShiShen("食神"),
    ShangGuan("伤官"), BiJian("比肩"), JieCai("劫财"), ZhengCai("正财"), PianCai("偏财");

    companion object {
        fun get(master: TianGan, another: TianGan): ShiShens {
            val wuXingRelation = master.wuXing relationTo another.wuXing
            val yinYangRelation = YinYangRelation.get(master.yinYang, another.yinYang)
            return when (wuXingRelation) {
                WuXingRelation.ShengWo -> if (yinYangRelation == YinYangRelation.TongXing) PianYin else ZhengYin
                WuXingRelation.WoSheng -> if (yinYangRelation == YinYangRelation.TongXing) ShiShen else ShangGuan
                WuXingRelation.KeWo -> if (yinYangRelation == YinYangRelation.TongXing) QiSha else ZhengGuan
                WuXingRelation.WoKe -> if (yinYangRelation == YinYangRelation.TongXing) PianCai else ZhengCai
                WuXingRelation.TongWo -> if (yinYangRelation == YinYangRelation.TongXing) BiJian else JieCai
            }
        }
    }
}