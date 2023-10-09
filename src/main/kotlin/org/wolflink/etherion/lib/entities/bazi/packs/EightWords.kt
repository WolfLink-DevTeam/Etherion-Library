package org.wolflink.etherion.lib.entities.bazi.packs

import org.wolflink.etherion.lib.entities.calendars.FateCalendar

/**
 * 只包含简单的天干地支八个字
 */
class EightWords(
    fateCalendar: FateCalendar
) : AbstractWords(fateCalendar) {
    fun toTwelveWords() = TwelveWords(fateCalendar)
}
