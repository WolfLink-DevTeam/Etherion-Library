package entities.calendars

import entities.BaseBaZi
import entities.timeunits.SolarMDH
import enums.*
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
    var fateYear : Int = 0
    // 1为立春~惊蛰 1月，2为惊蛰~清明 2月，以此类推，0为未初始化
    var fateMonth : Int = 0

    // 命历日 = 真太阳日，如果命历时在当天的23点及以后，则命历日+1
    var fateDay : Int = 0

    // 命历时 = 真太阳时
    var fateHour : Int = 0
    init {

        // 太阳历时间对象
        val year = solarCalendar.solarCalendar.get(Calendar.YEAR)
        val month = solarCalendar.solarCalendar.get(Calendar.MONTH)
        val day = solarCalendar.solarCalendar.get(Calendar.DAY_OF_MONTH)
        val hour = solarCalendar.solarCalendar.get(Calendar.HOUR_OF_DAY)

        val solarMDH = SolarMDH(month+1,day,hour)

        //是否达到立春
        fateYear = if((solarMDH >= SolarTerm.LiChun.solarMDH)) year else year - 1
        val solarTerms = SolarTerm.values()

        for (i in solarTerms.indices)
        {
            if(i >= solarTerms.size)break
            if(solarMDH.inRange(solarTerms[i].solarMDH,solarTerms[(i+1)%solarTerms.size].solarMDH))fateMonth = i+1
//            else println("${solarMDH.month} 不存在区间内 ${solarTerms[i].solarMDH.month} ${solarTerms[(i+1)%solarTerms.size].solarMDH.month}")
        }

        fateHour = solarCalendar.solarCalendar.get(Calendar.HOUR_OF_DAY)

        fateDay = solarCalendar.solarCalendar.get(Calendar.DAY_OF_MONTH)
        if(fateHour >= 23)fateDay++

    }
    // 将生辰历转为基础八字对象
    fun toBaseBaZi(name : String,sex : Sex,birthplace : String) : BaseBaZi
    {
        return BaseBaZi(name,sex,birthplace,solarCalendar,getYearGanZhi(),getMonthGanZhi(),getDayGanZhi(),getHourGanZhi())
    }

    // 获取年柱 天干-地支
    fun getYearGanZhi() : Pair<TianGan, DiZhi>
    {
        val tianGanIndex = if((fateYear - 3) % 10 == 0) 10 else (fateYear - 3) % 10
        val diZhiIndex = if((fateYear - 3) % 12 == 0) 12 else (fateYear - 3) % 12
        val tianGan = TianGan.values()[tianGanIndex - 1]
        val diZhi = DiZhi.values()[diZhiIndex - 1]
        return Pair(tianGan, diZhi)
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
    fun getMonthGanZhi() : Pair<TianGan,DiZhi>
    {
        var fateMonth = fateMonth
        if(fateMonth !in 1..12) throw IllegalArgumentException("不合规范(1~12)的命历月份，异常值为：$fateMonth")
        val yearColumn = getYearGanZhi()
        val monthGan : TianGan = when(yearColumn.first)
        {
            TianGan.Jia,TianGan.Ji -> TianGan.values()[(TianGan.Bing.ordinal + fateMonth - 1) % 10]
            TianGan.Yi,TianGan.Geng -> TianGan.values()[(TianGan.Wu.ordinal + fateMonth - 1) % 10]
            TianGan.Bing,TianGan.Xin -> TianGan.values()[(TianGan.Geng.ordinal + fateMonth - 1) % 10]
            TianGan.Ding,TianGan.Ren -> TianGan.values()[(TianGan.Ren.ordinal + fateMonth - 1) % 10]
            TianGan.Wu,TianGan.Gui -> TianGan.values()[(TianGan.Jia.ordinal + fateMonth - 1) % 10]
        }
        if(fateMonth >= 11) fateMonth -= 12
        val monthZhi : DiZhi = DiZhi.values()[fateMonth + 1]
        return monthGan to monthZhi
    }
    // 获取日天干 日地支
    fun getDayGanZhi() : Pair<TianGan,DiZhi>
    {
        val solarYear = solarCalendar.solarCalendar.get(Calendar.YEAR)
        val solarMonth = solarCalendar.solarCalendar.get(Calendar.MONTH) + 1
        val index = ((solarYear - 1) * 5 + (solarYear - 1)/4 + Month.getDays(solarYear,solarMonth,fateDay)) % 60
        val tianGanIndex = if(index % 10 == 0)10 else index % 10
        val diZhiIndex = if(index % 12 == 0)12 else index % 12
        val tianGan = TianGan.values()[tianGanIndex-1]
        val diZhi = DiZhi.values()[diZhiIndex-1]
        return tianGan to diZhi
    }
    /**
     * 获取时天干 时地支
     *
     * 甲己还加甲，乙庚丙作初
     * 丙辛从戊起，丁壬庚子居
     * 戊癸何方发，壬子是真途
      */
    fun getHourGanZhi() : Pair<TianGan,DiZhi>
    {
        val dayColumn = getDayGanZhi()
        val offset = ((fateHour + 1)%24)/2
        val diZhi : DiZhi = DiZhi.values()[offset % 12]
        val tianGan : TianGan = when(dayColumn.first)
        {
            TianGan.Jia,TianGan.Ji -> {
                TianGan.values()[(TianGan.Jia.ordinal + offset) % 10]
            }
            TianGan.Yi,TianGan.Geng -> {
                TianGan.values()[(TianGan.Bing.ordinal + offset) % 10]
            }
            TianGan.Bing,TianGan.Xin -> {
                TianGan.values()[(TianGan.Wu.ordinal + offset) % 10]
            }
            TianGan.Ding,TianGan.Ren -> {
                TianGan.values()[(TianGan.Geng.ordinal + offset) % 10]
            }
            TianGan.Wu,TianGan.Gui -> {
                TianGan.values()[(TianGan.Ren.ordinal + offset) % 10]
            }
        }
        return tianGan to diZhi
    }

    // 检查四柱准确性
}