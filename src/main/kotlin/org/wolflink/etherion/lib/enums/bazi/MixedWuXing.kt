package org.wolflink.etherion.lib.enums.bazi

import org.wolflink.etherion.lib.enums.base.TianGan

data class MixedWuXing(val nativeTianGan: TianGan,
                       val middleTianGan: TianGan,
                       val remnantTianGan: TianGan
) {
    fun getWuXing() : Triple<WuXing, WuXing, WuXing>
    = Triple(nativeTianGan.wuXing,middleTianGan.wuXing,remnantTianGan.wuXing)
    companion object {
        /**
         * 获取气的力量比例
         * 6成 3成 1成
         */
        val powers = listOf(0.6,0.3,0.1)
    }
}
