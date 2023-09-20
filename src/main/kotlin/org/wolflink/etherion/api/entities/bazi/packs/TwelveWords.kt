package org.wolflink.etherion.api.entities.bazi.packs

import org.wolflink.etherion.api.enums.base.DiZhi
import org.wolflink.etherion.api.enums.base.GanZhiWord
import org.wolflink.etherion.api.enums.base.TianGan

/**
 * 包含天干地支8字+流年大运4字
 */
class TwelveWords(
    words: List<GanZhiWord>
): AbstractWords(words) {
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