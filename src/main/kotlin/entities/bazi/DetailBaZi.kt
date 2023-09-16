package entities.bazi

import entities.calendars.FateCalendar
import entities.timeunits.SolarMDH
import enums.Gender
import enums.date.SolarTerm
import java.util.*

/**
 * 详细的八字信息
 * 包含以下参数：姓名，性别，出生地，真太阳时，年柱干支，月柱干支，日柱干支，时柱干支
 *
 * 详细的年柱(十神、星运、自坐、空亡、神煞)、详细的月柱、详细的日柱、详细的时柱
 * 详细的五行关系，生克合化等
 */

open class DetailBaZi(
    val name: String,
    val gender: Gender,
    val birthplace: String,
    val fateCalendar: FateCalendar
) {
    val yearPillar: BaZiPillar
    val monthPillar: BaZiPillar
    val dayPillar: BaZiPillar
    val hourPillar: BaZiPillar

    init {
        val master = fateCalendar.getDayGanZhi().first
        this.yearPillar = BaZiPillar(master, fateCalendar.getYearGanZhi())
        this.monthPillar = BaZiPillar(master, fateCalendar.getMonthGanZhi())
        this.dayPillar = BaZiPillar(master, fateCalendar.getDayGanZhi())
        this.hourPillar = BaZiPillar(master, fateCalendar.getHourGanZhi())
    }

    fun show() {
        val format = "%-2s | %-2s | %-2s | %-2s | %-2s"
        println(String.format(format, "日期", "年柱", "月柱", "日柱", "时柱"))
        println(
            String.format(
                format,
                "主星",
                yearPillar.primaryStar.chineseName,
                monthPillar.primaryStar.chineseName,
                dayPillar.primaryStar.chineseName,
                hourPillar.primaryStar.chineseName
            )
        )
        println(
            String.format(
                format,
                "天干",
                yearPillar.pillar.first.chineseName,
                monthPillar.pillar.first.chineseName,
                dayPillar.pillar.first.chineseName,
                hourPillar.pillar.first.chineseName
            )
        )
        println(
            String.format(
                format,
                "地支",
                yearPillar.pillar.second.chineseName,
                monthPillar.pillar.second.chineseName,
                dayPillar.pillar.second.chineseName,
                hourPillar.pillar.second.chineseName
            )
        )
        println(
            String.format(
                format,
                "藏干",
                yearPillar.pillar.second.nativeTianGan.chineseName,
                monthPillar.pillar.second.nativeTianGan.chineseName,
                dayPillar.pillar.second.nativeTianGan.chineseName,
                hourPillar.pillar.second.nativeTianGan.chineseName
            )
        )
        println(
            String.format(
                format,
                "藏干",
                yearPillar.pillar.second.middleTianGan.chineseName,
                monthPillar.pillar.second.middleTianGan.chineseName,
                dayPillar.pillar.second.middleTianGan.chineseName,
                hourPillar.pillar.second.middleTianGan.chineseName
            )
        )
        println(
            String.format(
                format,
                "藏干",
                yearPillar.pillar.second.remnantTianGan.chineseName,
                monthPillar.pillar.second.remnantTianGan.chineseName,
                dayPillar.pillar.second.remnantTianGan.chineseName,
                hourPillar.pillar.second.remnantTianGan.chineseName
            )
        )
        println(
            String.format(
                format,
                "副星",
                yearPillar.accessoryStars.first.chineseName,
                monthPillar.accessoryStars.first.chineseName,
                dayPillar.accessoryStars.first.chineseName,
                hourPillar.accessoryStars.first.chineseName
            )
        )
        println(
            String.format(
                format,
                "副星",
                yearPillar.accessoryStars.second.chineseName,
                monthPillar.accessoryStars.second.chineseName,
                dayPillar.accessoryStars.second.chineseName,
                hourPillar.accessoryStars.second.chineseName
            )
        )
        println(
            String.format(
                format,
                "副星",
                yearPillar.accessoryStars.third.chineseName,
                monthPillar.accessoryStars.third.chineseName,
                dayPillar.accessoryStars.third.chineseName,
                hourPillar.accessoryStars.third.chineseName
            )
        )
        println(
            String.format(
                format,
                "星运",
                yearPillar.xingYun.chineseName,
                monthPillar.xingYun.chineseName,
                dayPillar.xingYun.chineseName,
                hourPillar.xingYun.chineseName
            )
        )
        println(
            String.format(
                format,
                "自坐",
                yearPillar.ziZuo.chineseName,
                monthPillar.ziZuo.chineseName,
                dayPillar.ziZuo.chineseName,
                hourPillar.ziZuo.chineseName
            )
        )
//        println("五行关系 >>")
//        relationalBaZi.show()
    }

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
    fun checkDeviationDetails(): List<Pair<Double, String>> {
        val list = mutableListOf<Pair<Double, String>>()
        fateCalendar.solarCalendar.realCalendar.apply {
            // 太阳历时间对象
            val year = get(Calendar.YEAR)
            val month = get(Calendar.MONTH)
            val day = get(Calendar.DAY_OF_MONTH)
            val hour = get(Calendar.HOUR_OF_DAY)
            val min = get(Calendar.MINUTE)
            val solarMDH = SolarMDH(month + 1, day, hour)
            val totalHours = solarMDH.getTotalHours()
            // 立春附近检查
            if (totalHours - SolarTerm.LiChun.getSolarMDH(year).getTotalHours() in -24..24) list.add(20.0 to "立春附近24小时内")
            // 节气附近检查
            for (solarTerm in SolarTerm.values()) {
                if (totalHours - solarTerm.getSolarMDH(year).getTotalHours() in -24..24) list.add(10.0 to "节气附近24小时内")
            }
            // 时辰分界线检查
            if (hour % 2 == 0 && min >= 55) list.add(10.0 to "临近时辰分界线5分钟以内")
            if (hour % 2 != 0 && min <= 5) list.add(10.0 to "临近时辰分界线5分钟以内")
        }
        return list
    }
}