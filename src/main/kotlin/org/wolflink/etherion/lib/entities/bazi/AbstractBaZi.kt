package org.wolflink.etherion.lib.entities.bazi

import org.wolflink.etherion.lib.entities.bazi.packs.AbstractWords
import org.wolflink.etherion.lib.entities.bazi.packs.BaZiPillar
import org.wolflink.etherion.lib.entities.calendars.FateCalendar
import org.wolflink.etherion.lib.entities.deviation.DeviationTable
import org.wolflink.etherion.lib.enums.Gender
import org.wolflink.etherion.lib.enums.base.TianGan
import org.wolflink.etherion.lib.enums.base.WangShuai
import org.jetbrains.annotations.TestOnly

abstract class AbstractBaZi(
    val name: String = "",
    val gender: Gender = Gender.MALE,
    val birthplace: String = "",
    val fateCalendar: FateCalendar,
    val words: AbstractWords,
) {
    val master: TianGan = fateCalendar.getDayGanZhi().first
    val yearPillar: BaZiPillar = BaZiPillar(master, fateCalendar.getYearGanZhi())
    val monthPillar: BaZiPillar = BaZiPillar(master, fateCalendar.getMonthGanZhi())
    val dayPillar: BaZiPillar = BaZiPillar(master, fateCalendar.getDayGanZhi())
    val hourPillar: BaZiPillar = BaZiPillar(master, fateCalendar.getHourGanZhi())
    abstract fun getBaZiRelation(): BaZiRelation
    /**
     * 获取当前日主旺衰的具体值
     * Pair<HelpValue,restrainValue>
     */
    abstract fun getWangShuaiValue(): Pair<Double,Double>
    /**
     * 获取当前日主旺衰
     * 静态盘为固定值
     * 动态盘为动态计算值，与流年大运有关
     */
    abstract fun getWangShuai(): WangShuai
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
    abstract fun checkDeviation(): DeviationTable

    /**
     * 展示命盘信息
     */
    @TestOnly
    abstract fun show()
}