package org.wolflink.etherion.lib.entities.smallsixren

import org.wolflink.etherion.lib.entities.calendars.FateCalendar
import org.wolflink.etherion.lib.enums.base.*
import org.wolflink.etherion.lib.enums.sixren.SixGod
import org.wolflink.etherion.lib.enums.sixren.SixPalace
import org.wolflink.etherion.lib.enums.sixren.SixRelative
import org.wolflink.etherion.lib.expansions.forEach

/**
 * 小六壬卦盘
 *
 * @param tripleNumber  三个起占数字
 * @param monthDiZhi    月建
 * @param hourDiZhi     时辰
 */
class SmallSixRen(val fateCalendar: FateCalendar,val tripleNumber: Triple<Short,Short,Short>) {

    val monthDiZhi: DiZhi = fateCalendar.getMonthGanZhi().second
    val hourDiZhi: DiZhi = fateCalendar.getHourGanZhi().second

    val triplePalace: Triple<SSRPalace,SSRPalace,SSRPalace>

    /**
     * 小六壬宫位初始化
     */
    init {
        val skySSRPalace: SSRPalace
        val earthSSRPalace: SSRPalace
        val personSSRPalace: SSRPalace
        run {
            val skySixPalace = SixPalace.DaAn.next(tripleNumber.first-1)
            skySSRPalace = SSRPalace(
                SanCai.SKY,
                skySixPalace,
                hourDiZhi.yinYang,
                monthDiZhi,
                hourDiZhi
            )
        }
        run {
            val earthSixPalace = skySSRPalace.palace.next(tripleNumber.second-1)
            earthSSRPalace = SSRPalace(
                SanCai.EARTH,
                earthSixPalace,
                if(skySSRPalace.palace.yinYang != earthSixPalace.yinYang) YinYang.Yang else YinYang.Yin,
                monthDiZhi,
                hourDiZhi
            )
        }
        run {
            val personSixPalace = earthSSRPalace.palace.next(tripleNumber.third-1)
            personSSRPalace = SSRPalace(
                SanCai.PERSON,
                personSixPalace,
                if(earthSSRPalace.palace.yinYang != personSixPalace.yinYang) YinYang.Yang else YinYang.Yin,
                monthDiZhi,
                hourDiZhi
            )
        }
        triplePalace = Triple(skySSRPalace,earthSSRPalace,personSSRPalace)
    }
    /**
     * 六神六亲延迟初始化
     */
    init {
        val qingLongPalace = triplePalace.third.palace.next(hourDiZhi.ordinal)
        triplePalace.forEach {
            var palaceIndexDelta = it.palace.ordinal - qingLongPalace.ordinal
            if(palaceIndexDelta < 0) palaceIndexDelta += SixPalace.entries.size
            it.sixGod = SixGod.QingLong.next(palaceIndexDelta)
            it.relative = SixRelative.fromWuXingRelation(
                (triplePalace.third.palaceDiZhi relationTo it.palaceDiZhi).first
            )
        }
    }
}