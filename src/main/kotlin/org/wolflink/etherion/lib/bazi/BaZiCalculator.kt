package org.wolflink.etherion.lib.bazi

import org.wolflink.etherion.lib.entities.bazi.AbstractBaZi
import org.wolflink.etherion.lib.entities.bazi.packs.AbstractWords
import org.wolflink.etherion.lib.enums.bazi.MixedTianGan
import org.wolflink.etherion.lib.enums.base.WangShuai
import org.wolflink.etherion.lib.enums.base.relationTo

/**
 * 八字计算器
 * 提供各种八字相关的数值运算支持
 * 也提供一些底层概念的解释
 */
object BaZiCalculator {

    /**
     * 权重系数，会自动规范化，总和100为标准参考
     * 10  12  0   12  15  15
     * 8   40  14  10  15  15
     * 年  月  日   时  大运 流年
     */
    private val wangShuaiValueList = listOf(10, 8, 12, 40, 0, 14, 12, 10, 15, 15, 15, 15)
    private val monthMasterPowers = listOf(0.8,0.15,0.05)
    fun calcWangShuaiValue(eightWords: AbstractWords): Pair<Double, Double> {
        val master = eightWords[4]
        var helpWeights = 0.0
        var restrainWeights = 0.0
        val normalizationValue = 100.0 / wangShuaiValueList.subList(0, eightWords.size).reduce { a, b -> a + b }
        for (index in 0 until eightWords.size) {
            if (index == 3) continue // 暂时跳过月令
            if (index == 4) continue // 跳过日主
            val tripleRelation = (master relationTo eightWords[index]).toList()
            for (relationIndex in 0..2) {
                val power = MixedTianGan.triplePowers[relationIndex]
                val relation = tripleRelation[relationIndex]
                helpWeights += (wangShuaiValueList[index] * normalizationValue * power * relation.helpWeight)
                restrainWeights += (wangShuaiValueList[index] * normalizationValue * power * relation.restrainWeight)
            }
        }
        // 月令
        val tripleRelation = (master relationTo eightWords[3]).toList()
        for (i in 0..2) {
            helpWeights += wangShuaiValueList[3] * normalizationValue * tripleRelation[i].helpWeight * monthMasterPowers[i]
            restrainWeights += wangShuaiValueList[3] * normalizationValue * tripleRelation[i].restrainWeight * monthMasterPowers[i]
        }
        return helpWeights to restrainWeights
    }
    /**
     * 定日主旺衰
     */
    fun calcWangShuai(baZi: AbstractBaZi): WangShuai {
        val wangShuaiValue = calcWangShuaiValue(baZi.words)
        return calcWangShuai(wangShuaiValue)
    }

    fun calcWangShuai(wangShuaiValue: Pair<Double, Double>): WangShuai {
        val delta = wangShuaiValue.first - wangShuaiValue.second
        return when {
            delta >= 40 -> WangShuai.Wang
            delta >= 15 -> WangShuai.Xiang
            delta >= -10 -> WangShuai.Xiu
            delta >= -35 -> WangShuai.Qiu
            else -> WangShuai.Si
        }
    }
}