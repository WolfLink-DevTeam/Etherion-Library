package org.wolflink.etherion.api.entities.calendars

import org.wolflink.etherion.api.entities.bazi.StaticBaZi
import org.wolflink.etherion.api.enums.Gender
import org.wolflink.etherion.api.enums.base.DiZhi
import org.wolflink.etherion.api.enums.base.TianGan
import org.wolflink.etherion.api.enums.date.Month
import org.wolflink.etherion.api.enums.date.SolarTerm
import org.wolflink.etherion.api.expansions.inRange
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

    companion object {
        // 获取年柱 天干-地支
        fun getYearGanZhi(fateYear: Int): Pair<TianGan, DiZhi> {
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
        fun getMonthGanZhi(fateYear: Int, fateMonth: Int): Pair<TianGan,DiZhi> {
            var varMonth = fateMonth
            if (varMonth !in 1..12) throw IllegalArgumentException("不合规范(1~12)的命历月份，异常值为：$varMonth")
            val yearColumn = getYearGanZhi(fateYear)
            val monthGan: TianGan = when (yearColumn.first) {
                TianGan.Jia, TianGan.Ji -> TianGan.entries[(TianGan.Bing.ordinal + varMonth - 1) % 10]
                TianGan.Yi, TianGan.Geng -> TianGan.entries[(TianGan.Wu.ordinal + varMonth - 1) % 10]
                TianGan.Bing, TianGan.Xin -> TianGan.entries[(TianGan.Geng.ordinal + varMonth - 1) % 10]
                TianGan.Ding, TianGan.Ren -> TianGan.entries[(TianGan.Ren.ordinal + varMonth - 1) % 10]
                TianGan.Wu, TianGan.Gui -> TianGan.entries[(TianGan.Jia.ordinal + varMonth - 1) % 10]
            }
            if (varMonth >= 11) varMonth -= 12
            val monthZhi: DiZhi = DiZhi.entries[varMonth + 1]
            return monthGan to monthZhi
        }
        // 获取日天干 日地支
        fun getDayGanZhi(solarYear: Int, solarMonth: Int, fateDay: Int): Pair<TianGan, DiZhi> {
            val index = ((solarYear - 1) * 5 + (solarYear - 1) / 4 + Month.getDays(solarYear, solarMonth, fateDay)) % 60
            val tianGanIndex = if (index % 10 == 0) 10 else index % 10
            val diZhiIndex = if (index % 12 == 0) 12 else index % 12
            val tianGan = TianGan.entries[tianGanIndex - 1]
            val diZhi = DiZhi.entries[diZhiIndex - 1]
            return tianGan to diZhi
        }
        /**
         * 获取时天干 时地支
         *
         * 甲己还加甲，乙庚丙作初
         * 丙辛从戊起，丁壬庚子居
         * 戊癸何方发，壬子是真途
         */
        fun getHourGanZhi(solarYear: Int, fateMonth: Int, fateDay: Int, fateHour: Int): Pair<TianGan, DiZhi> {
            val dayColumn = getDayGanZhi(solarYear,fateMonth,fateDay)
            val offset = ((fateHour + 1) % 24) / 2
            val diZhi: DiZhi = DiZhi.entries[offset % 12]
            val tianGan: TianGan = when (dayColumn.first) {
                TianGan.Jia, TianGan.Ji -> {
                    TianGan.entries[(TianGan.Jia.ordinal + offset) % 10]
                }

                TianGan.Yi, TianGan.Geng -> {
                    TianGan.entries[(TianGan.Bing.ordinal + offset) % 10]
                }

                TianGan.Bing, TianGan.Xin -> {
                    TianGan.entries[(TianGan.Wu.ordinal + offset) % 10]
                }

                TianGan.Ding, TianGan.Ren -> {
                    TianGan.entries[(TianGan.Geng.ordinal + offset) % 10]
                }

                TianGan.Wu, TianGan.Gui -> {
                    TianGan.entries[(TianGan.Ren.ordinal + offset) % 10]
                }
            }
            return tianGan to diZhi
        }
    }

    // 命历年份，如果太阳历月份日数在立春之前，则视为还在上一年，即未过立春则 命历年 = 太阳历年 - 1
    var fateYear: Int = 0

    // 1为立春~惊蛰 1月，2为惊蛰~清明 2月，以此类推，-999为未初始化
    var fateMonth: Int = -999

    // 命历日 = 真太阳日，如果命历时在当天的23点及以后，则命历日+1
    var fateDay: Int = 0

    // 命历时 = 真太阳时
    var fateHour: Int = 0

    init {

        // 生辰历时间对象，年份跟立春有关
        val calendar = solarCalendar.realCalendar.clone() as Calendar
        val year = calendar.get(Calendar.YEAR)
        //是否达到立春
        fateYear = if (calendar >= SolarTerm.LiChun.getExactTime(year)) year else year - 1
        calendar.set(Calendar.YEAR,fateYear)
        val solarTerms = SolarTerm.entries.toTypedArray()

        for (i in solarTerms.indices step 2) {
            val startTerm = solarTerms[i]
            val endTerm = solarTerms[(i+2)%solarTerms.size]
            val start = startTerm.getExactTime(fateYear)
            val end = endTerm.getExactTime(fateYear)
            if (calendar.inRange(start, end)) {
                fateMonth = i/2 + 1
            }
//          else println("${solarMDH.month} 不存在区间内 ${solarTerms[i].solarMDH.month} ${solarTerms[(i+1)%solarTerms.size].solarMDH.month}")
        }

        fateHour = solarCalendar.realCalendar.get(Calendar.HOUR_OF_DAY)

        fateDay = solarCalendar.realCalendar.get(Calendar.DAY_OF_MONTH)
        if (fateHour >= 23) fateDay++

    }

    // 将生辰历转为详细八字对象
    fun toDetailBaZi(name: String, gender: Gender, birthplace: String): StaticBaZi {
        return StaticBaZi(
            name,
            gender,
            birthplace,
            this
        )
    }
    fun getYearGanZhi(): Pair<TianGan, DiZhi> = Companion.getYearGanZhi(fateYear)
    fun getMonthGanZhi(): Pair<TianGan, DiZhi> = Companion.getMonthGanZhi(fateYear,fateMonth)
    fun getDayGanZhi(): Pair<TianGan, DiZhi> = Companion.getDayGanZhi(solarCalendar.realCalendar[Calendar.YEAR],solarCalendar.realCalendar[Calendar.MONTH]+1,fateDay)
    fun getHourGanZhi(): Pair<TianGan, DiZhi> = getHourGanZhi(solarCalendar.realCalendar[Calendar.YEAR],solarCalendar.realCalendar[Calendar.MONTH]+1,fateDay,fateHour)
}