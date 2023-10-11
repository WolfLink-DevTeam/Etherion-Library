package org.wolflink.etherion.lib.enums.bazi

import org.wolflink.etherion.lib.enums.base.TianGan

/**
 * 混合天干
 * 本气
 * 中气
 * 余气
 */
data class MixedTianGan(val nativeTianGan: TianGan,
                        val middleTianGan: TianGan,
                        val remnantTianGan: TianGan
) {
    fun getWuXing() : Triple<WuXing, WuXing, WuXing>
    = Triple(nativeTianGan.wuXing,middleTianGan.wuXing,remnantTianGan.wuXing)
    companion object {
        /**
         * 月令本中余力量比例
         */
        val monthMasterPowers = listOf(0.8,0.15,0.05)
        /**
         * 获取气的力量比例
         * 6成 3成 1成
         */
        val triplePowers = listOf(0.6,0.3,0.1)
    }
}
