package org.wolflink.etherion.api.bazi

import org.wolflink.etherion.api.entities.bazi.AbstractBaZi
import org.wolflink.etherion.api.entities.bazi.StaticBaZi
import org.wolflink.etherion.api.entities.bazi.packs.AbstractWords
import org.wolflink.etherion.api.enums.bazi.*

/**
 * 八字解释器
 */
object BaZiInterpreter {

    /**
     * 权重系数，会自动规范化，总和100为标准参考
     * 10  12  0   12  15  15
     * 8   40  14  10  15  15
     * 年  月  日   时  大运 流年
     */
    private val wangShuaiValueList = listOf(10, 8, 12, 40, 0, 14, 12, 10, 15, 15, 15, 15)
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
                val power = MixedWuXing.powers[relationIndex]
                val relation = tripleRelation[relationIndex]

                helpWeights += (wangShuaiValueList[index] * normalizationValue * power * relation.helpWeight)
                restrainWeights += (wangShuaiValueList[index] * normalizationValue * power * relation.restrainWeight)
            }
        }
        // 月令
        val tripleRelation = (master relationTo eightWords[3]).toList()
        helpWeights += wangShuaiValueList[3] * normalizationValue * tripleRelation[0].helpWeight * 0.8
        helpWeights += wangShuaiValueList[3] * normalizationValue * tripleRelation[1].helpWeight * 0.15
        helpWeights += wangShuaiValueList[3] * normalizationValue * tripleRelation[2].helpWeight * 0.05
        restrainWeights += wangShuaiValueList[3] * normalizationValue * tripleRelation[0].restrainWeight * 0.8
        restrainWeights += wangShuaiValueList[3] * normalizationValue * tripleRelation[1].restrainWeight * 0.15
        restrainWeights += wangShuaiValueList[3] * normalizationValue * tripleRelation[2].restrainWeight * 0.05
        //TODO
        println("助力值：${helpWeights.toInt()}")
        println("克制值：${restrainWeights.toInt()}")

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

    /**
     * 八字整体分析
     * 包含日主身强身弱信息
     * 五行旺衰信息
     * 用神忌神信息
     */
    fun overallEvaluation(staticBaZi: StaticBaZi) {
        // 日主
        val dayMaster = staticBaZi.dayPillar.pillar.first
        // 月令
        val monthDecree = staticBaZi.monthPillar.pillar.second
    }

}