package org.wolflink.etherion.api.utils

/**
 * 真太阳时偏差校准
 */
object SolarDeviation {

    private val deviationMap: Map<Pair<Int,Int>,Int> =
        ResourceUtil.loadResource("data/SolarDeviation").split('\n').associate { line ->
            val args = line.split(' ')
            val (month,day) = args[0].split('-')
            (month.toInt() to day.toInt()) to args[1].toInt()
        }

    // 获取偏差时间(秒)
    fun getDeviation(month : Int,day : Int) : Int
    {
        return deviationMap[month to day] ?: throw IllegalArgumentException("无法根据给定月日找到匹配的偏差值。月：$month，日：$day")
    }

}