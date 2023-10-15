package org.wolflink.etherion.lib.bazi.alg

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.wolflink.etherion.lib.entities.bazi.AbstractBaZi
import org.wolflink.etherion.lib.entities.bazi.DynamicBaZi
import org.wolflink.etherion.lib.entities.bazi.packs.QuantizationBaZi
import org.wolflink.etherion.lib.enums.bazi.MixedTianGan
import org.wolflink.etherion.lib.enums.base.WuXing
import java.lang.Exception

/**
 * 计算动态八字盘的五行比例
 *
 * [0]: 起始查询年份，例如：2010 代表从2010年开始进行数据计算
 * [1]: 查询数组长度，例如：100 代表从起始年份开始连续查询100年
 * [2]: 是否忽略复杂元素反应，如刑冲克破合化等，true 则忽略
 */
data object WuXingPercentChart: BaZiAlgorithm() {
    override fun checkInput(abstractBaZi: AbstractBaZi, arguments: Array<out Any>): Boolean {
        if(abstractBaZi !is DynamicBaZi) return false
        return try {
            arguments[0] as Int
            arguments[1] as Int
            arguments[2] as Boolean
            true
        } catch (ignore: Exception) {
            false
        }
    }

    override fun compute(abstractBaZi: AbstractBaZi, arguments: Array<out Any>): JsonElement {
        abstractBaZi as DynamicBaZi
        val ja = JsonArray()
        val startYear = arguments[0] as Int
        val queryLength = arguments[1] as Int

        val wuXingJOMap = mutableMapOf<WuXing,JsonObject>()
        // 初始化
        for (wuXing in WuXing.entries) {
            val jo = JsonObject()
            jo.addProperty("年份",wuXing.chineseName+"比例")
            wuXingJOMap[wuXing] = jo
        }
        abstractBaZi.apply {
            val originYear = queryYear
            for (i in 0..queryLength) {
                updateLuckPillars(startYear+i)
                val map = getWuXingPercentWithElementReaction(this)
                for (entry in map) {
                    wuXingJOMap[entry.key]?.addProperty("$queryYear",entry.value)
                }
            }
            updateLuckPillars(originYear)
        }
        for (entry in wuXingJOMap) {
            ja.add(entry.value)
        }
        return ja
    }
    private
    fun getWuXingPercentWithElementReaction(abstractBaZi: AbstractBaZi):Map<WuXing,Double> {
        val relation = abstractBaZi.getBaZiRelation()
        val quantizationBaZi = QuantizationBaZi(abstractBaZi)
        val map = mutableMapOf<WuXing,Double>()
        // 初始化
        for (wuXing in WuXing.entries) {
            map[wuXing] = 0.0
        }
        // TODO 暂未实现，不清楚反应跟元素之间的关系
        /**
         *考虑刑克合化情况比较复杂
         * 需要动态修改元素以及相应位置的权重
         * 还要考虑合成是否成立，是合而化还是合而不化
         *
         * 六冲：冲凶、冲旺、冲动、冲开、冲出、冲去、冲破、冲空
         *
         * 三合逢冲则破
         * 六合需要考虑元素力量，力量不同结果不同，因此六合应该在最后考虑
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