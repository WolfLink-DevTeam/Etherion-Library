package org.wolflink.etherion.api.entities.bazi.packs

import org.wolflink.etherion.api.entities.calendars.FateCalendar
import org.wolflink.etherion.api.enums.base.DiZhi
import org.wolflink.etherion.api.enums.base.GanZhiWord
import org.wolflink.etherion.api.enums.base.TianGan
import java.util.Calendar

/**
 * 包含天干地支8字+流年大运4字
 */
class TwelveWords(
    fateCalendar: FateCalendar
): AbstractWords(fateCalendar) {

    //TODO 更改 year 更新 extraFourWords
    var year: Int = 0
    var extraFourWords: List<GanZhiWord>? = null
    fun toEightWords() = EightWords(fateCalendar)
}