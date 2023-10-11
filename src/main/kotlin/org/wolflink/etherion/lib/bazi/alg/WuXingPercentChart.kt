package org.wolflink.etherion.lib.bazi.alg

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.wolflink.etherion.lib.entities.bazi.AbstractBaZi
import org.wolflink.etherion.lib.entities.bazi.packs.QuantizationBaZi
import org.wolflink.etherion.lib.enums.bazi.MixedTianGan
import org.wolflink.etherion.lib.enums.bazi.WuXing

data object WuXingPercentChart: BaZiAlgorithm() {
    override fun checkInput(abstractBaZi: AbstractBaZi, arguments: Array<out Any>): Boolean {
        return false
    }

    override fun compute(abstractBaZi: AbstractBaZi, arguments: Array<out Any>): JsonElement {
        return JsonObject()
    }
    fun getWuXingPercentWithElementReaction(abstractBaZi: AbstractBaZi):Map<WuXing,Double> {
        val relation = abstractBaZi.getBaZiRelation()
        val quantizationBaZi = QuantizationBaZi(abstractBaZi)
        val map = mutableMapOf<WuXing,Double>()
        // 初始化
        for (wuXing in WuXing.entries) {
            map[wuXing] = 0.0
        }
        // TODO 暂未实现
        /**
         *考虑刑克合化情况比较复杂
         * 需要动态修改元素以及相应位置的权重
         * 还要考虑合成是否成立，是合而化还是合而不化
         *
         * 优先级：
         */
        return getWuXingPercentIgnoreElementReaction(abstractBaZi)
    }
    /**
     * 获取五行比例，忽略元素反应(考虑月令，未考虑刑克合化)
     */
    fun getWuXingPercentIgnoreElementReaction(abstractBaZi: AbstractBaZi):Map<WuXing,Double> {
        val quantizationBaZi = QuantizationBaZi(abstractBaZi)
        val map = mutableMapOf<WuXing,Double>()
        // 初始化
        for (wuXing in WuXing.entries) {
            map[wuXing] = 0.0
        }
        // 统计
        for (entry in abstractBaZi.words.withIndex()) {
            var i = 0
            entry.value.getWuXing().toList().forEach {
                map[it] = map[it]!! + quantizationBaZi.wuXingWeightList[entry.index] * MixedTianGan.triplePowers[i++]
            }
        }
        return map
    }
}