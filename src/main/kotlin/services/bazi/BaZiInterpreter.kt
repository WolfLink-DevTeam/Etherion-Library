package services.bazi

import entities.bazi.StandardBaZi
import enums.bazi.*

/**
 * 八字解释器
 */
object BaZiInterpreter {


    /**
     * 总和 0~99 便于映射
     */
    private val wangShuaiValueList = listOf(8,4,12,39,0,14,12,10)

    fun calcWangShuaiValue(standardBaZi: StandardBaZi): Double {
        val master = standardBaZi.eightWords[4]
        var helpWeights = 0.0
        var restrainWeights = 0.0
        for (index in 0..7) {
            if(index == 3) continue // 暂时跳过月令
            if(index == 4) continue // 跳过日主
            val tripleRelation = (master relationTo standardBaZi.eightWords[index]).toList()
            for (relationIndex in 0..2) {
                val power = MixedWuXing.powers[relationIndex]
                val relation = tripleRelation[relationIndex]

                helpWeights += (wangShuaiValueList[index] * power * relation.helpWeight)
                restrainWeights += (wangShuaiValueList[index] * power * relation.restrainWeight)
            }
        }
        val tripleRelation = (master relationTo standardBaZi.eightWords[3]).toList()
        helpWeights += wangShuaiValueList[3] * tripleRelation[0].helpWeight * 0.8
        helpWeights += wangShuaiValueList[3] * tripleRelation[1].helpWeight * 0.15
        helpWeights += wangShuaiValueList[3] * tripleRelation[2].helpWeight * 0.05
        restrainWeights += wangShuaiValueList[3] * tripleRelation[0].restrainWeight * 0.8
        restrainWeights += wangShuaiValueList[3] * tripleRelation[1].restrainWeight * 0.15
        restrainWeights += wangShuaiValueList[3] * tripleRelation[2].restrainWeight * 0.05
        //TODO
        println("助力值：${helpWeights.toInt()}")
        println("克制值：${restrainWeights.toInt()}")

        return helpWeights - restrainWeights
    }
    /**
     * 定日主旺衰
     * 8 12 0 12
     * 4 40 14 10
     */
    fun calcWangShuai(standardBaZi: StandardBaZi) : WangShuai {
        val wangShuaiValue = calcWangShuaiValue(standardBaZi)
        return when {
            wangShuaiValue >= 40 -> WangShuai.Wang
            wangShuaiValue >= 15 -> WangShuai.Xiang
            wangShuaiValue >= -10 -> WangShuai.Xiu
            wangShuaiValue >= -35 -> WangShuai.Qiu
            else -> WangShuai.Si
        }
    }
    fun calcWangShuai(wangShuaiValue: Double) : WangShuai {
        return when {
            wangShuaiValue >= 40 -> WangShuai.Wang
            wangShuaiValue >= 15 -> WangShuai.Xiang
            wangShuaiValue >= -10 -> WangShuai.Xiu
            wangShuaiValue >= -35 -> WangShuai.Qiu
            else -> WangShuai.Si
        }
    }

    /**
     * 八字整体分析
     * 包含日主身强身弱信息
     * 五行旺衰信息
     * 用神忌神信息
     */
    fun overallEvaluation(standardBaZi: StandardBaZi) {
        // 日主
        val dayMaster = standardBaZi.dayPillar.pillar.first
        // 月令
        val monthDecree = standardBaZi.monthPillar.pillar.second
    }

}