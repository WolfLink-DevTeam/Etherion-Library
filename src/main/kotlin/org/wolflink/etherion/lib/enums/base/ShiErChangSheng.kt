package org.wolflink.etherion.lib.enums.base

enum class ShiErChangSheng(val chineseName: String) {
    ChangSheng("长生"), MuYu("沐浴"), GuanDai("冠带"),
    LinGuan("临官"), DiWang("帝旺"), Shuai("衰"),
    Bing("病"), Si("死"), Mu("墓"),
    Jue("绝"), Tai("胎"), Yang("养");
    companion object {

        private fun getIndex(startDiZhi: DiZhi, positive: Boolean, diZhi: DiZhi): Int {
            if (positive) {
                var delta = diZhi.ordinal - startDiZhi.ordinal
                if (delta < 0) delta += 12
                return delta
            } else {
                var delta = startDiZhi.ordinal - diZhi.ordinal
                if (delta < 0) delta += 12
                return delta
            }
        }

        /**
         * 天干地支十二长生表
         */
        fun get(tianGan: TianGan, diZhi: DiZhi): ShiErChangSheng {
            // 十二长生索引
            val index = when (tianGan) {
                TianGan.Jia -> getIndex(DiZhi.Hai, true, diZhi)
                TianGan.Yi -> getIndex(DiZhi.Wu, false, diZhi)
                TianGan.Bing -> getIndex(DiZhi.Yin, true, diZhi)
                TianGan.Ding -> getIndex(DiZhi.You, false, diZhi)
                TianGan.Wu -> getIndex(DiZhi.Yin, true, diZhi)
                TianGan.Ji -> getIndex(DiZhi.You, false, diZhi)
                TianGan.Geng -> getIndex(DiZhi.Si, true, diZhi)
                TianGan.Xin -> getIndex(DiZhi.Zi, false, diZhi)
                TianGan.Ren -> getIndex(DiZhi.Shen, true, diZhi)
                TianGan.Gui -> getIndex(DiZhi.Mao, false, diZhi)
            }
            return entries[index]
        }

        /**
         * 五行十二长生表(注意区别天干地支十二长生表)
         */
        fun get(wuXing: WuXing, diZhi: DiZhi): ShiErChangSheng {
            val index = when(wuXing) {
                WuXing.Mu -> getIndex(DiZhi.Hai,true,diZhi)
                WuXing.Huo -> getIndex(DiZhi.Yin,true,diZhi)
                WuXing.Jin -> getIndex(DiZhi.Si,true,diZhi)
                WuXing.Shui,WuXing.Tu -> getIndex(DiZhi.Shen,true,diZhi)
            }
            return entries[index]
        }
    }
}