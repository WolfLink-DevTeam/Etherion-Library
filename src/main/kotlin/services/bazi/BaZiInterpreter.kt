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
    /**
     * 定日主旺衰
     * 8 12 0 12
     * 4 40 14 10
     */
    fun calcWangShuai(standardBaZi: StandardBaZi) : WangShuai {
        val master = standardBaZi.eightWords[4]
        var wangShuaiValue = 0.0
        for (index in 0..7) {
            if(index == 4) continue
            val tripleRelation = (master relationTo standardBaZi.eightWords[index]).toList()
            for (index in 0..2) {
                val power = MixedWuXing.powers[index]
                val relation = tripleRelation[index]
                if(relation == WuXingRelation.ShengWo || relation == WuXingRelation.TongWo) {
                    wangShuaiValue += (wangShuaiValueList[index] * power)
                }
            }
        }
        return WangShuai.entries[(wangShuaiValue / 20).toInt()]
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