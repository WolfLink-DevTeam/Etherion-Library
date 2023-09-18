package entities.bazi.packs

import enums.base.DiZhi
import enums.base.GanZhiWord
import enums.base.TianGan
import kotlin.math.min

/**
 * 包含天干地支8字+流年大运4字
 */
class TwelveWords(
    val words: List<GanZhiWord>
): List<GanZhiWord> by words {
    constructor(yearPillar: Pair<TianGan, DiZhi>,
                monthPillar: Pair<TianGan, DiZhi>,
                dayPillar: Pair<TianGan, DiZhi>,
                hourPillar: Pair<TianGan, DiZhi>,
                majorLuckPillar: Pair<TianGan, DiZhi>,
                minorLuckPillar: Pair<TianGan, DiZhi>
    ) : this(
        listOf(
            yearPillar.first,yearPillar.second,
            monthPillar.first,monthPillar.second,
            dayPillar.first,dayPillar.second,
            hourPillar.first,hourPillar.second,
            majorLuckPillar.first,majorLuckPillar.second,
            minorLuckPillar.first,minorLuckPillar.second
        ))
    fun toEightWords() = EightWords(this)
}