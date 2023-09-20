package org.wolflink.etherion.api.entities.purplestar

import org.wolflink.etherion.api.entities.calendars.FateCalendar
import org.wolflink.etherion.api.enums.Gender
import org.wolflink.etherion.api.enums.base.DiZhi
import org.wolflink.etherion.api.enums.base.TianGan
import org.wolflink.etherion.api.enums.purplestar.PalaceType

/**
 * 紫微斗数 天星盘
 *
 * @property twelvePalaces 十二宫，索引 0 为子宫，1为丑，以此类推
 */
class TianBoard(
    val name: String,
    val gender: Gender,
    val birthplace: String,
    val fateCalendar: FateCalendar
) {
    val twelvePalaces: MutableList<Palace> = mutableListOf()
    val mingPalaceIndex : Int = 1 + fateCalendar.fateMonth - (fateCalendar.getHourGanZhi().second).ordinal

    init {
        val yearPillar = fateCalendar.getYearGanZhi()
        val firstTianGanIndex = (yearPillar.first.ordinal * 2 + 2)%10
        for (index in 0..11)
        {
            val deltaIndex = (index + 12 - mingPalaceIndex)%12
            if(index < 2) twelvePalaces.add(Palace(PalaceType.values()[0+deltaIndex],TianGan.values()[firstTianGanIndex+index], DiZhi.values()[index]))
            else twelvePalaces.add(
                Palace(
                    PalaceType.values()[0+deltaIndex],
                    TianGan.values()[(firstTianGanIndex+index-2)%10],
                    DiZhi.values()[index]))
        }
    }
}