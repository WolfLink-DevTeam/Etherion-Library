package org.wolflink.etherion.lib.bazi.alg

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.wolflink.etherion.lib.entities.bazi.AbstractBaZi
import org.wolflink.etherion.lib.entities.bazi.DynamicBaZi
import java.lang.Exception

/**
 * [0]: 起始旺衰查询年份
 * [1]: 查询数组长度
 */
data object WangShuaiMap : BaZiAlgorithm() {
    override fun checkInput(abstractBaZi: AbstractBaZi,vararg arguments: Any): Boolean {
        if(abstractBaZi !is DynamicBaZi) return false
        return try {
            arguments[0] as Int
            arguments[1] as Int
            true
        } catch (ignore: Exception) {
            false
        }
    }

    override fun compute(abstractBaZi: AbstractBaZi,vararg arguments: Any): JsonElement {
        abstractBaZi as DynamicBaZi
        var startYear = arguments[0] as Int
        val queryLength = arguments[1] as Int
        val ja = JsonArray()
        val help = JsonObject()
        val restraint = JsonObject()
        val delta = JsonObject()
        help.addProperty("年份","生助值")
        help.addProperty("年份","克泄值")
        help.addProperty("年份","旺衰值")
        abstractBaZi.apply {
            // 备份年份
            val originYear = queryYear
            for (i in 0..queryLength) {
                updateLuckPillars(startYear+i)
                val pair = getWangShuaiValue()
                help.addProperty("$queryYear",pair.first)
                restraint.addProperty("$queryYear",pair.second)
                delta.addProperty("$queryYear",pair.first - pair.second)
            }
            // 回溯
            updateLuckPillars(originYear)
        }
        ja.add(help)
        ja.add(restraint)
        ja.add(delta)
        return ja
    }
}