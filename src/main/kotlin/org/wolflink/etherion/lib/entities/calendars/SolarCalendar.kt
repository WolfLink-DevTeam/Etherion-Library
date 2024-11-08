package org.wolflink.etherion.lib.entities.calendars

import org.wolflink.etherion.lib.dao.SolarDeviation
import java.util.Calendar

/**
 * 太阳历日期
 *
 * 遵循以下规则：
 * 指定基本计量单位为秒，不需要毫秒那样精确
 * 由阳历计算而来，基本上偏差都在2小时以内，与经度关系较大，其次是年份导致的微小偏差
 *
 * 计算公式：真太阳时 = 北京时间 + 4 分钟 x (当地经度 - 120度) + 当日补偿
 *
 * @param calendar      阳历日期
 * @param longitude     经度，如 112.5度，不要分秒的形式
 * @param needDeviation 是否需要校准精确的真太阳时，如果不校准会有几分钟到十几分钟的误差
 */
data class SolarCalendar(private val calendar: Calendar,private val longitude : Double,private val needDeviation : Boolean = true)
{
    /**
     * 原始日期Calendar对象(可能经过了真太阳时校准，存在几分钟偏差)
     */
    val realCalendar : Calendar = calendar.clone() as Calendar

    init {
        // Java-Calendar 中，0为1月，11为12月
        val month = realCalendar.get(Calendar.MONTH)
        val day = realCalendar.get(Calendar.DAY_OF_MONTH)
        if(needDeviation) realCalendar.add(Calendar.SECOND, SolarDeviation.getDeviation(month + 1, day))
        // 以出生地时间为准，不需要使用北京时间，以下代码废弃
//        realCalendar.add(Calendar.SECOND, ((longitude - 120) * 240).roundToInt())
    }

    fun show()
    {
        println("""
        平太阳时 ${calendar.get(Calendar.YEAR)}年${calendar.get(Calendar.MONTH)+1}月${calendar.get(Calendar.DAY_OF_MONTH)}日${calendar.get(Calendar.HOUR_OF_DAY)}时${calendar.get(Calendar.MINUTE)}分${calendar.get(Calendar.SECOND)}秒
        经度 $longitude
        真太阳时 ${realCalendar.get(Calendar.YEAR)}年${realCalendar.get(Calendar.MONTH)+1}月${realCalendar.get(Calendar.DAY_OF_MONTH)}日${realCalendar.get(Calendar.HOUR_OF_DAY)}时${realCalendar.get(Calendar.MINUTE)}分${realCalendar.get(Calendar.SECOND)}秒
    """.trimIndent())
    }
}
