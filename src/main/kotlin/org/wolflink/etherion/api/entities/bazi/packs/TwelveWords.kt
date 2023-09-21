package org.wolflink.etherion.api.entities.bazi.packs

import org.wolflink.etherion.api.entities.calendars.FateCalendar
import org.wolflink.etherion.api.enums.base.DiZhi
import org.wolflink.etherion.api.enums.base.GanZhiWord
import org.wolflink.etherion.api.enums.base.TianGan
import java.util.Calendar
import java.util.stream.Stream

/**
 * 包含天干地支8字+流年大运4字
 */
class TwelveWords(
    fateCalendar: FateCalendar
): AbstractWords(fateCalendar) {
    override val size: Int = 12
    override fun get(index: Int): GanZhiWord {
        return if(index in 0..7) eightWords[index]
        else extraFourWords!![index-8]
    }

    //TODO 更改 year 更新 extraFourWords
    var year: Int = 0
    private var extraFourWords: List<GanZhiWord>? = null

    override fun iterator(): Iterator<GanZhiWord> {
        val li = mutableListOf<GanZhiWord>()
        for (word in eightWords) li.add(word)
        if(extraFourWords != null) {
            for (word in extraFourWords!!) li.add(word)
        }
        return li.iterator()
    }
    fun updateLuckPillars(majorLuckPillar: BaZiPillar,minorLuckPillar: BaZiPillar) {
        extraFourWords = listOf(
            majorLuckPillar.pillar.first,
            majorLuckPillar.pillar.second,
            minorLuckPillar.pillar.first,
            minorLuckPillar.pillar.second
        )
    }
    fun toEightWords() = EightWords(fateCalendar)
}