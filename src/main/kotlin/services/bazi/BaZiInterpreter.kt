package services.bazi

import entities.bazi.StandardBaZi

/**
 * 八字解释器
 */
object BaZiInterpreter {

    /**
     * 定旺衰
     */
    fun dingWangShuai() {
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