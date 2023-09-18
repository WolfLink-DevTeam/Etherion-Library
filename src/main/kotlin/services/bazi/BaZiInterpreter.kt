package services.bazi

import entities.bazi.StaticBaZi
import enums.bazi.*

/**
 * 八字解释器
 */
object BaZiInterpreter {


    /**
     * 总和 0~99 便于映射
     */
    private val wangShuaiValueList = listOf(8,4,12,39,0,14,12,10)

    fun calcWangShuaiValue(staticBaZi: StaticBaZi): Pair<Double,Double> {
        val master = staticBaZi.eightWords[4]
        var helpWeights = 0.0
        var restrainWeights = 0.0
        for (index in 0..7) {
            if(index == 3) continue // 暂时跳过月令
            if(index == 4) continue // 跳过日主
            val tripleRelation = (master relationTo staticBaZi.eightWords[index]).toList()
            for (relationIndex in 0..2) {
                val power = MixedWuXing.powers[relationIndex]
                val relation = tripleRelation[relationIndex]

                helpWeights += (wangShuaiValueList[index] * power * relation.helpWeight)
                restrainWeights += (wangShuaiValueList[index] * power * relation.restrainWeight)
            }
        }
        val tripleRelation = (master relationTo staticBaZi.eightWords[3]).toList()
        helpWeights += wangShuaiValueList[3] * tripleRelation[0].helpWeight * 0.8
        helpWeights += wangShuaiValueList[3] * tripleRelation[1].helpWeight * 0.15
        helpWeights += wangShuaiValueList[3] * tripleRelation[2].helpWeight * 0.05
        restrainWeights += wangShuaiValueList[3] * tripleRelation[0].restrainWeight * 0.8
        restrainWeights += wangShuaiValueList[3] * tripleRelation[1].restrainWeight * 0.15
        restrainWeights += wangShuaiValueList[3] * tripleRelation[2].restrainWeight * 0.05
        //TODO
        println("助力值：${helpWeights.toInt()}")
        println("克制值：${restrainWeights.toInt()}")

        return helpWeights to restrainWeights
    }
    /**
     * 定日主旺衰
     * 8 12 0 12
     * 4 40 14 10
     */
    fun calcWangShuai(staticBaZi: StaticBaZi) : WangShuai {
        val wangShuaiValue = calcWangShuaiValue(staticBaZi)
        return calcWangShuai(wangShuaiValue)
    }
    fun calcWangShuai(wangShuaiValue: Pair<Double,Double>) : WangShuai {
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