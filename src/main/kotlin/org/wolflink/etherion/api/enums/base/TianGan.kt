package org.wolflink.etherion.api.enums.base

import org.wolflink.etherion.api.enums.bazi.WuXing

enum class TianGan(@JvmField val chineseName : Char,
                   @JvmField val yinYang : YinYang,
                   @JvmField val wuXing: WuXing
) : GanZhiWord {
    Jia('甲', YinYang.Yang, WuXing.Mu),
    Yi('乙', YinYang.Yin, WuXing.Mu),
    Bing('丙', YinYang.Yang, WuXing.Huo),
    Ding('丁', YinYang.Yin, WuXing.Huo),
    Wu('戊', YinYang.Yang, WuXing.Tu),
    Ji('己', YinYang.Yin, WuXing.Tu),
    Geng('庚', YinYang.Yang, WuXing.Jin),
    Xin('辛', YinYang.Yin, WuXing.Jin),
    Ren('壬', YinYang.Yang, WuXing.Shui),
    Gui('癸', YinYang.Yin, WuXing.Shui);

    override fun getChineseName(): Char = chineseName
    override fun getWuXing(): Triple<WuXing, WuXing, WuXing> {
        return Triple(wuXing,wuXing,wuXing)
    }
    override fun getYinYang(): YinYang = yinYang
}