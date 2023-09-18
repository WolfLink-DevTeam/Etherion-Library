package entities.bazi

import enums.base.DiZhi
import enums.base.GanZhiWord
import enums.base.TianGan

/**
 * 只包含简单的天干地支八个字
 */
class EightWords(
    val words: List<GanZhiWord>
): List<GanZhiWord> by words {
    constructor(yearPillar: Pair<TianGan, DiZhi>,
                monthPillar: Pair<TianGan, DiZhi>,
                dayPillar: Pair<TianGan, DiZhi>,
                hourPillar: Pair<TianGan, DiZhi>) : this(
        listOf(
            yearPillar.first,yearPillar.second,
            monthPillar.first,monthPillar.second,
            dayPillar.first,dayPillar.second,
            hourPillar.first,hourPillar.second
        ))
}
