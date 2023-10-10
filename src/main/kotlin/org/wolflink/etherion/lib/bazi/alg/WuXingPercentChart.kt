package org.wolflink.etherion.lib.bazi.alg

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.wolflink.etherion.lib.entities.bazi.AbstractBaZi
import org.wolflink.etherion.lib.enums.bazi.WuXing
import org.wolflink.etherion.lib.expansions.ConstValues

data object WuXingPercentChart: BaZiAlgorithm() {
    override fun checkInput(abstractBaZi: AbstractBaZi, arguments: Array<out Any>): Boolean {
        return false
    }

    override fun compute(abstractBaZi: AbstractBaZi, arguments: Array<out Any>): JsonElement {
        return JsonObject()
    }

    /**
     * 获取五行比例，忽略元素反应(考虑月令，未考虑刑克合化)
     */
    fun getWuXingPercentIgnoreElementReaction(abstractBaZi: AbstractBaZi):Map<WuXing,Double> {
        val size = abstractBaZi.words.size
        val map = mutableMapOf<WuXing,Double>()
        for (wuXing in WuXing.entries) {
            map[wuXing] = 0.0
        }
        for (entry in abstractBaZi.words.withIndex()) {
            var i = 0
            if(size == 8) {
                entry.value.getWuXing().toList().forEach {
                    map[it] = map[it]!! + ConstValues.wuXingValue8List[entry.index] * ConstValues.triplePowers[i++]
                }
            }
            if(size == 12) {
                entry.value.getWuXing().toList().forEach {
                    map[it] = map[it]!! + ConstValues.wuXingValue12List[entry.index] * ConstValues.triplePowers[i++]
                }
            }
        }
        return map
    }
}