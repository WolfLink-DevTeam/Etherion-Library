package org.wolflink.etherion.lib.entities.smallsixren

import org.wolflink.etherion.lib.enums.base.*
import org.wolflink.etherion.lib.enums.sixren.SixGod
import org.wolflink.etherion.lib.enums.sixren.SixPalace
import org.wolflink.etherion.lib.enums.sixren.SixRelative

/**
 * 小六壬宫位
 * 宫位地支，十二长生，旺衰等都需要通过 SmallSixRen 类进行初始化
 * 该对象只能够保存相关数据
 * @param type          三才
 * @param palace        宫位
 * @param palaceDiZhi   宫位地支
 * @param monthDiZhi    月建
 * @param hourDiZhi     时辰
 */
data class SSRPalace(
    val type: SanCai,
    val palace: SixPalace,
    val yinYang: YinYang,
    val monthDiZhi: DiZhi,
    val hourDiZhi: DiZhi
) {
    val palaceDiZhi: DiZhi
    val wangShuai: WangShuai
    val changSheng: ShiErChangSheng

    lateinit var sixGod: SixGod
    lateinit var relative: SixRelative

    init {
        palaceDiZhi = if(yinYang == YinYang.Yang) palace.yangDiZhi else palace.yinDiZhi

        val monthWuXing = monthDiZhi.getWuXing().first
        val thisWuXing = palaceDiZhi.getWuXing().first
        var deltaWuXing = thisWuXing.ordinal - monthWuXing.ordinal
        if(deltaWuXing <= -3) deltaWuXing += 5
        if(deltaWuXing >= 3) deltaWuXing -= 5
        wangShuai = when(deltaWuXing) {
            0 -> WangShuai.Wang
            1 -> WangShuai.Xiang
            -1 -> WangShuai.Xiu
            -2 -> WangShuai.Qiu
            2 -> WangShuai.Si
            else -> throw IllegalArgumentException("非法的参数：$deltaWuXing")
        }
        changSheng = ShiErChangSheng.get(palaceDiZhi.getWuXing().first,hourDiZhi)
    }
}
