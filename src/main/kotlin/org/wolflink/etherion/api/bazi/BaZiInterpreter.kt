package org.wolflink.etherion.api.bazi

import org.wolflink.etherion.api.entities.bazi.StaticBaZi
import org.wolflink.etherion.api.enums.bazi.ShiShens
import org.wolflink.etherion.api.enums.bazi.WuXing
import org.wolflink.etherion.api.enums.bazi.relationTo
import org.wolflink.etherion.api.enums.bazi.string
import kotlin.math.roundToInt

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
        // 旺衰
        val wangShuai = staticBaZi.getWangShuai()
        val wangShuaiValue = staticBaZi.getWangShuaiValue()
        // 五行比例
        val wuXingList = mutableListOf<Pair<WuXing,Double>>()
        for (wuXing in WuXing.entries) {
            wuXingList.add(wuXing to staticBaZi.getWuXingPercent(wuXing))
        }
        wuXingList.sortBy { pair -> -pair.second }
        // 喜神

        // 忌神

        println("日主 ${dayMaster.chineseName}${dayMaster.wuXing.chineseName} , 生于 ${staticBaZi.fateCalendar.fateYear} 年 ${monthDecree.chineseName}月")
        println("日主旺衰程度 ${wangShuai.chineseName}")
        println("生助值 ${wangShuaiValue.first.roundToInt()} 克泄值 ${wangShuaiValue.second.roundToInt()}")
        println("十神旺衰程度")
        for (pair in wuXingList) {
            println(pair.first.chineseName+" "+ShiShens.get(dayMaster.wuXing,pair.first).string()+" "+(pair.second * 100).roundToInt()+"%")
        }
    }
}