package org.wolflink.etherion.lib.bazi.alg

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.wolflink.etherion.lib.entities.bazi.AbstractBaZi
import org.wolflink.etherion.lib.entities.bazi.DynamicBaZi
import java.lang.Exception
import kotlin.math.abs

/**
 * 计算动态八字盘的流年大运影响下的日主旺衰走势
 *
 * [0]: 起始旺衰查询年份，例如：2010 代表从2010年开始进行数据计算
 * [1]: 查询数组长度，例如：100 代表从起始年份开始连续查询100年
 * [2]: 是否忽略复杂元素反应，如刑冲克破合化等，true 则忽略
 */
data object WangShuaiMap : BaZiAlgorithm() {
    override fun checkInput(abstractBaZi: AbstractBaZi,vararg arguments: Any): Boolean {
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

    override fun compute(abstractBaZi: AbstractBaZi,vararg arguments: Any): JsonElement {
        val ignoreElementReaction = arguments[2] as Boolean
        return if(ignoreElementReaction) computeIgnoreElementReaction(abstractBaZi as DynamicBaZi,arguments[0] as Int,arguments[1] as Int)
        else computeWithElementReaction(abstractBaZi as DynamicBaZi,arguments[0] as Int,arguments[1] as Int)
    }

    private fun computeWithElementReaction(dynamicBaZi: DynamicBaZi,startYear: Int,queryLength: Int): JsonElement {
        // TODO
        return JsonObject()
    }
    private fun computeIgnoreElementReaction(dynamicBaZi: DynamicBaZi,startYear: Int,queryLength: Int): JsonElement {
        val ja = JsonArray()
        val help = JsonObject()
        val restraint = JsonObject()
        val delta = JsonObject()
        help.addProperty("年份","生助值")
        help.addProperty("年份","克泄值")
        help.addProperty("年份","旺衰值")
        dynamicBaZi.apply {
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