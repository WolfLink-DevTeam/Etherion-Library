package entities

import entities.calendars.SolarCalendar
import entities.timeunits.SolarMDH
import enums.DiZhi
import enums.Sex
import enums.SolarTerm
import enums.TianGan
import java.util.*

/**
 * 基础的八字信息
 * 包含以下参数：姓名，性别，出生地，真太阳时，年柱干支，月柱干支，日柱干支，时柱干支
 */

class BaseBaZi(val name : String,
               val sex : Sex,
               val birthplace : String,
               val solarCalendar: SolarCalendar,
               val yearPillar : Pair<TianGan,DiZhi>,
               val monthPillar : Pair<TianGan,DiZhi>,
               val dayPillar : Pair<TianGan,DiZhi>,
               val hourPillar : Pair<TianGan,DiZhi>, ) {
    /**
     * 检查四柱准确度详细信息
     *
     * 生辰时间是否在立春前后24小时内
     * 月日是否临近节气前后24小时内
     * 生辰历的时是否刚好处于单数时分界线附近30分钟
     *
     * 存偏概率 0 ~ 100
     * 0为绝对不可能有误，100为绝对存在偏差
     */
    fun checkDeviationDetails() : List<Pair<Double,String>>
    {
        val list = mutableListOf<Pair<Double,String>>()
        // 太阳历时间对象
        val year = solarCalendar.solarCalendar.get(Calendar.YEAR)
        val month = solarCalendar.solarCalendar.get(Calendar.MONTH)
        val day = solarCalendar.solarCalendar.get(Calendar.DAY_OF_MONTH)
        val hour = solarCalendar.solarCalendar.get(Calendar.HOUR_OF_DAY)
        val min = solarCalendar.solarCalendar.get(Calendar.MINUTE)

        val solarMDH = SolarMDH(month + 1, day, hour)
        val totalHours = solarMDH.getTotalHours()
        // 立春附近检查
        if(totalHours - SolarTerm.LiChun.solarMDH.getTotalHours() in -24..24)list.add(20.0 to "立春附近24小时内")
        // 节气附近检查
        for (solarTerm in SolarTerm.values())
        {
            if(totalHours - solarTerm.solarMDH.getTotalHours() in -24..24)list.add(10.0 to "节气附近24小时内")
        }
        // 时辰分界线检查
        if(hour % 2 == 0 && min >= 55)list.add(10.0 to "临近时辰分界线5分钟以内")
        if(hour % 2 != 0 && min <= 5)list.add(10.0 to "临近时辰分界线5分钟以内")

        return list
    }
}