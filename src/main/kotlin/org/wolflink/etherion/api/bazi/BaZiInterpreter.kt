package org.wolflink.etherion.api.bazi

import org.wolflink.etherion.api.entities.bazi.AbstractBaZi
import org.wolflink.etherion.api.entities.bazi.StaticBaZi
import org.wolflink.etherion.api.entities.bazi.packs.AbstractWords
import org.wolflink.etherion.api.enums.bazi.*

/**
 * 八字解释器
 * 负责解析八字命盘，生成的信息便于用户理解
 */
object BaZiInterpreter {
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