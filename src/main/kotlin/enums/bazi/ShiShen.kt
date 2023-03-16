package enums.bazi

import enums.base.TianGan

enum class ShiShen(val chineseName : String) {
    ZhengGuan("正官"),QiSha("七杀"),ZhengYin("正印"),PianYin("偏印"), ShiShen_("食神"),
    ShangGuan("伤官"),BiJian("比肩"),JieCai("劫财"),ZhengCai("正财"),PianCai("偏财");
    companion object{
        fun get(master : TianGan,another : TianGan) : enums.bazi.ShiShen
        {
            val wuXingRelation = WuXingRelation.get(master.wuXing,another.wuXing)
            val yinYangRelation = YinYangRelation.get(master.yinYang,another.yinYang)
            return when(wuXingRelation)
            {
                WuXingRelation.ShengWo -> {
                    if(yinYangRelation == YinYangRelation.TongXing) {
                        PianYin
                    }else{
                        ZhengYin
                    }
                }
                WuXingRelation.WoSheng -> {
                    if(yinYangRelation == YinYangRelation.TongXing) {
                        ShiShen_
                    }else{
                        ShangGuan
                    }
                }
                WuXingRelation.KeWo -> {
                    if(yinYangRelation == YinYangRelation.TongXing) {
                        QiSha
                    }else{
                        ZhengGuan
                    }
                }
                WuXingRelation.WoKe -> {
                    if(yinYangRelation == YinYangRelation.TongXing) {
                        PianCai
                    }else{
                        ZhengCai
                    }
                }
                WuXingRelation.TongWo -> {
                    if(yinYangRelation == YinYangRelation.TongXing) {
                        BiJian
                    }else{
                        JieCai
                    }
                }
            }
        }
    }
}