package entities

import enums.DiZhi
import enums.Month
import enums.SolarTerm
import enums.TianGan
import java.util.*

/*
 * 生辰历(论命用的)，注意区别于太阳历
 *
 * 遵循以下规则：
 * 由太阳历计算而来，生辰历的年份 = 太阳历年份 (是否 -1 取决于月日是否达到立春)
 * 命历月 根据节气划分月份，而不是农历月
 * 命历日 在命历月的基础上有所变动，视立春为1月1日，以此类推
 */
class FateCalendar(val solarCalendar: SolarCalendar) {

    // 命历年份，如果太阳历月份日数在立春之前，则视为还在上一年，即未过立春则 命历年 = 太阳历年 - 1
    var fateYear: Int = 0

    // 1为立春~惊蛰 1月，2为惊蛰~清明 2月，以此类推，0为未初始化
    var fateMonth: Int = 0

    var fateDay: Int = 0

    // 命历时 = 真太阳时
    var fateHour: Int = 0

    init {

        // 太阳历时间对象
        val year = solarCalendar.solarCalendar.get(Calendar.YEAR)
        val month = solarCalendar.solarCalendar.get(Calendar.MONTH)
        val day = solarCalendar.solarCalendar.get(Calendar.DAY_OF_MONTH)
        val hour = solarCalendar.solarCalendar.get(Calendar.HOUR_OF_DAY)

        val solarMDH = SolarMDH(month + 1, day, hour)

        //是否达到立春
        fateYear = if ((solarMDH >= SolarTerm.LiChun.solarMDH)) year else year - 1
        val solarTerms = SolarTerm.values()

        for (i in solarTerms.indices) {
            if (solarMDH.inRange(solarTerms[i].solarMDH, solarTerms[(i + 1) % solarTerms.size].solarMDH)) fateMonth = i + 1
//          else println("${solarMDH.month} 不存在区间内 ${solarTerms[i].solarMDH.month} ${solarTerms[(i+1)%solarTerms.size].solarMDH.month}")
        }

    }

    // 获取年柱 天干-地支
    fun getYearGanZhi(): Pair<TianGan, DiZhi> {
        val tianGanIndex = when (val i = (fateYear - 3) % 10) {
            0 -> 9
            else -> i - 1
        }
        val diZhiIndex = when (val i = (fateYear - 3) % 12) {
            0 -> 11
            else -> i - 1
        }
        val tianGan = TianGan.values()[tianGanIndex]
        val diZhi = DiZhi.values()[diZhiIndex]
        return tianGan to diZhi
    }

    /**
     * 获取月天干 月地支
     *
     * 五虎遁，年上起月
     * 甲己(1,6)之年丙(3)作首，乙庚(2,7)之岁戊(5)为头
     * 丙辛(3,8)必定寻庚(7)起，丁壬(4,9)壬(9)位顺行流
     * 若问戊癸(5,0)何方发，甲(1)寅之年好追求
     *
     * 地支 1月为寅(3)，2月卯(4)，3月辰，以此类推，10月亥(12)，11月子(1)，12月丑(2)
     */
    fun getMonthGanZhi(): Pair<TianGan, DiZhi> {
        var fateMonth = fateMonth
        if (fateMonth !in 1..12) throw IllegalArgumentException("不合规范(1~12)的命历月份，异常值为：$fateMonth")
        val yearColumn = getYearGanZhi()
        val monthGan: TianGan = when (yearColumn.first) {
            TianGan.Jia, TianGan.Ji -> TianGan.values()[(TianGan.Bing.ordinal + fateMonth - 1) % 10]
            TianGan.Yi, TianGan.Geng -> TianGan.values()[(TianGan.Wu.ordinal + fateMonth - 1) % 10]
            TianGan.Bing, TianGan.Xin -> TianGan.values()[(TianGan.Geng.ordinal + fateMonth - 1) % 10]
            TianGan.Ding, TianGan.Ren -> TianGan.values()[(TianGan.Ren.ordinal + fateMonth - 1) % 10]
            TianGan.Wu, TianGan.Gui -> TianGan.values()[(TianGan.Jia.ordinal + fateMonth - 1) % 10]
        }
        if (fateMonth >= 11) fateMonth -= 12
        val monthZhi: DiZhi = DiZhi.values()[fateMonth + 1]
        return monthGan to monthZhi
    }

    // 获取日天干 日地支
    fun getDayGanZhi(): Pair<TianGan, DiZhi> {
        val solarYear = solarCalendar.solarCalendar.get(Calendar.YEAR)
        val solarMonth = solarCalendar.solarCalendar.get(Calendar.MONTH) + 1
        val solarDay = solarCalendar.solarCalendar.get(Calendar.DAY_OF_MONTH)
        val index = ((solarYear - 1) * 5 + (solarYear - 1) / 4 + Month.getDays(solarYear, solarMonth, solarDay)) % 60
        val tianGanIndex = if (index % 10 == 0) 10 else index % 10
        val diZhiIndex = if (index % 12 == 0) 12 else index % 12
        val tianGan = TianGan.values()[tianGanIndex - 1]
        val diZhi = DiZhi.values()[diZhiIndex - 1]
        return tianGan to diZhi
    }

    /**
     * 获取时天干 时地支
     *
     * 甲己还加甲，乙庚丙作初
     * 丙辛从戊起，丁壬庚子居
     * 戊癸何方发，壬子是真途
     */
    fun getHourGanZhi(): Pair<TianGan, DiZhi> {
        val solarHour = solarCalendar.solarCalendar.get(Calendar.HOUR_OF_DAY)
        val dayColumn = getDayGanZhi()
        val diZhi: DiZhi = DiZhi.values()[((solarHour + 1) / 2) % 12]
        val tianGan: TianGan = when (dayColumn.first) {
            TianGan.Jia, TianGan.Ji -> {
                TianGan.values()[(TianGan.Jia.ordinal + (solarHour + 1) / 2) % 10]
            }

            TianGan.Yi, TianGan.Geng -> {
                TianGan.values()[(TianGan.Bing.ordinal + (solarHour + 1) / 2) % 10]
            }

            TianGan.Bing, TianGan.Xin -> {
                TianGan.values()[(TianGan.Wu.ordinal + (solarHour + 1) / 2) % 10]
            }

            TianGan.Ding, TianGan.Ren -> {
                TianGan.values()[(TianGan.Geng.ordinal + (solarHour + 1) / 2) % 10]
            }

            TianGan.Wu, TianGan.Gui -> {
                TianGan.values()[(TianGan.Ren.ordinal + (solarHour + 1) / 2) % 10]
            }
        }
        return tianGan to diZhi
    }

}