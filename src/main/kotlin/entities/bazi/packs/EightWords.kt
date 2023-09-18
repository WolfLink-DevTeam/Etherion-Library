package entities.bazi.packs

import enums.base.DiZhi
import enums.base.GanZhiWord
import enums.base.TianGan

/**
 * 只包含简单的天干地支八个字
 */
class EightWords(
    words: List<GanZhiWord>
) : AbstractWords(words) {
    constructor(
        yearPillar: Pair<TianGan, DiZhi>,
        monthPillar: Pair<TianGan, DiZhi>,
        dayPillar: Pair<TianGan, DiZhi>,
        hourPillar: Pair<TianGan, DiZhi>
    ) : this(
        listOf(
            yearPillar.first, yearPillar.second,
            monthPillar.first, monthPillar.second,
            dayPillar.first, dayPillar.second,
            hourPillar.first, hourPillar.second
        )
    )

    fun toTwelveWords(majorLuckPillar: Pair<TianGan, DiZhi>, minorLuckPillar: Pair<TianGan, DiZhi>) = TwelveWords(
        listOf(
            *words.toTypedArray(),
            majorLuckPillar.first,
            majorLuckPillar.second,
            minorLuckPillar.first,
            minorLuckPillar.second
        )
    )
}
