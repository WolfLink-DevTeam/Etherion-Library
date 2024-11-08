package org.wolflink.etherion.lib.entities.bazi.packs

import org.wolflink.etherion.lib.entities.calendars.FateCalendar
import org.wolflink.etherion.lib.enums.base.DiZhi
import org.wolflink.etherion.lib.enums.base.GanZhiWord
import org.wolflink.etherion.lib.enums.base.TianGan

abstract class AbstractWords(
    val fateCalendar: FateCalendar,
    protected val eightWords: List<GanZhiWord>
): List<GanZhiWord> by eightWords {
    constructor(fateCalendar: FateCalendar): this(
        fateCalendar,
        fateCalendar.getYearGanZhi(),
        fateCalendar.getMonthGanZhi(),
        fateCalendar.getDayGanZhi(),
        fateCalendar.getHourGanZhi()
    )
    constructor(
        fateCalendar: FateCalendar,
        yearPillar: Pair<TianGan, DiZhi>,
        monthPillar: Pair<TianGan, DiZhi>,
        dayPillar: Pair<TianGan, DiZhi>,
        hourPillar: Pair<TianGan, DiZhi>
    ) : this(
        fateCalendar,
        listOf(
            yearPillar.first, yearPillar.second,
            monthPillar.first, monthPillar.second,
            dayPillar.first, dayPillar.second,
            hourPillar.first, hourPillar.second
        )
    )
}