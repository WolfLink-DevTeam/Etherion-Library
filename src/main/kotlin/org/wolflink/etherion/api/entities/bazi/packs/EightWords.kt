package org.wolflink.etherion.api.entities.bazi.packs

import org.wolflink.etherion.api.entities.calendars.FateCalendar
import org.wolflink.etherion.api.enums.base.DiZhi
import org.wolflink.etherion.api.enums.base.GanZhiWord
import org.wolflink.etherion.api.enums.base.TianGan

/**
 * 只包含简单的天干地支八个字
 */
class EightWords(
    fateCalendar: FateCalendar
) : AbstractWords(fateCalendar) {
    fun toTwelveWords() = TwelveWords(fateCalendar)
}
