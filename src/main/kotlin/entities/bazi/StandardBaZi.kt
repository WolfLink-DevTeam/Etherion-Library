package entities.bazi

import entities.calendars.FateCalendar
import entities.deviation.DeviationRecord
import entities.deviation.DeviationTable
import entities.timeunits.SolarMDH
import enums.Gender
import enums.bazi.WangShuai
import enums.date.SolarTerm
import services.bazi.BaZiInterpreter
import java.util.*

/**
 * 详细的八字信息
 * 包含以下参数：姓名，性别，出生地，真太阳时，年柱干支，月柱干支，日柱干支，时柱干支
 *
 * 详细的年柱(十神、星运、自坐、空亡、神煞)、详细的月柱、详细的日柱、详细的时柱
 * 详细的五行关系，生克合化等
 */

open class StandardBaZi(
    val name: String = "",
    val gender: Gender = Gender.MALE,
    val birthplace: String = "",
    fateCalendar: FateCalendar
) : AbstractBazi(fateCalendar) {
    fun show() {
        fateCalendar.solarCalendar.show()
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
                yearPillar.pillar.second.mixedWuXing.nativeTianGan.chineseName,
                monthPillar.pillar.second.mixedWuXing.nativeTianGan.chineseName,
                dayPillar.pillar.second.mixedWuXing.nativeTianGan.chineseName,
                hourPillar.pillar.second.mixedWuXing.nativeTianGan.chineseName
            )
        )
        println(
            String.format(
                format,
                "藏干",
                yearPillar.pillar.second.mixedWuXing.middleTianGan.chineseName,
                monthPillar.pillar.second.mixedWuXing.middleTianGan.chineseName,
                dayPillar.pillar.second.mixedWuXing.middleTianGan.chineseName,
                hourPillar.pillar.second.mixedWuXing.middleTianGan.chineseName
            )
        )
        println(
            String.format(
                format,
                "藏干",
                yearPillar.pillar.second.mixedWuXing.remnantTianGan.chineseName,
                monthPillar.pillar.second.mixedWuXing.remnantTianGan.chineseName,
                dayPillar.pillar.second.mixedWuXing.remnantTianGan.chineseName,
                hourPillar.pillar.second.mixedWuXing.remnantTianGan.chineseName
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
        println("日主旺衰 ${BaZiInterpreter.calcWangShuai(this).chineseName}")
//        println("五行关系 >>")
//        relationalBaZi.show()
    }

    private var wangShuai: WangShuai? = null
    /**
     * 获取当前日主旺衰
     * 静态盘为固定值
     * 动态盘为动态计算值，与流年大运有关
     */
    override fun getWangShuai(): WangShuai {
        if(wangShuai == null) wangShuai = BaZiInterpreter.calcWangShuai(this)
        return wangShuai!!
    }


    override fun checkDeviation(): DeviationTable {
        val deviationTable = DeviationTable()
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
            if (totalHours - SolarTerm.LiChun.getSolarMDH(year).getTotalHours() in -24..24) deviationTable.add(DeviationRecord.LiChunDeviation)
            // 节气附近检查
            for (solarTerm in SolarTerm.values()) {
                if (totalHours - solarTerm.getSolarMDH(year).getTotalHours() in -24..24) deviationTable.add(DeviationRecord.SolarTermDeviation)
            }
            // 时辰分界线检查
            if (hour % 2 == 0 && min >= 55) deviationTable.add(DeviationRecord.HourDeviation)
            if (hour % 2 != 0 && min <= 5) deviationTable.add(DeviationRecord.HourDeviation)
        }
        return deviationTable
    }
}