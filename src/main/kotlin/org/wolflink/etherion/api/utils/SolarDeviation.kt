package org.wolflink.etherion.api.utils

import org.wolflink.etherion.api.configs.SolarDeviationConfig

/**
 * 真太阳时偏差校准
 */
object SolarDeviation {

    // 获取偏差时间(秒)
    fun getDeviation(month : Int,day : Int) : Int
    {
        return SolarDeviationConfig.solarDeviationMap[month to day] ?: throw IllegalArgumentException("无法根据给定月日找到匹配的偏差值。月：$month，日：$day")
    }

}