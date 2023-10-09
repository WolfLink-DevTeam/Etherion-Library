package org.wolflink.etherion.lib.enums.base

import org.wolflink.etherion.lib.enums.bazi.MixedWuXing
import org.wolflink.etherion.lib.enums.bazi.WuXing
import kotlin.enums.EnumEntries

enum class DiZhi(
    @JvmField val chineseName: Char,
    @JvmField val yinYang: YinYang,
    @JvmField val mixedWuXing: MixedWuXing
) : GanZhiWord {
    Zi('子', YinYang.Yang,MixedWuXing(TianGan.Gui, TianGan.Gui, TianGan.Gui)),
    Chou('丑', YinYang.Yin, MixedWuXing(TianGan.Ji, TianGan.Gui, TianGan.Xin)),
    Yin('寅', YinYang.Yang, MixedWuXing(TianGan.Jia, TianGan.Bing, TianGan.Wu)),
    Mao('卯', YinYang.Yin, MixedWuXing(TianGan.Yi, TianGan.Yi, TianGan.Yi)),
    Chen('辰', YinYang.Yang, MixedWuXing(TianGan.Wu, TianGan.Yi, TianGan.Gui)),
    Si('巳', YinYang.Yin, MixedWuXing(TianGan.Bing, TianGan.Geng, TianGan.Wu)),
    Wu('午', YinYang.Yang, MixedWuXing(TianGan.Ding, TianGan.Ji, TianGan.Ding)),
    Wei('未', YinYang.Yin, MixedWuXing(TianGan.Ji, TianGan.Ding, TianGan.Yi)),
    Shen('申', YinYang.Yang, MixedWuXing(TianGan.Geng, TianGan.Ren, TianGan.Wu)),
    You('酉', YinYang.Yin, MixedWuXing(TianGan.Xin, TianGan.Xin, TianGan.Xin)),
    Xu('戌', YinYang.Yang, MixedWuXing(TianGan.Wu, TianGan.Xin, TianGan.Ding)),
    Hai('亥', YinYang.Yin, MixedWuXing(TianGan.Ren, TianGan.Jia, TianGan.Ren));

    override val iEntries: EnumEntries<*>
        get() = entries
    override fun getChineseName(): Char = chineseName
    override fun getWuXing(): Triple<WuXing, WuXing, WuXing> = mixedWuXing.getWuXing()
    override fun getYinYang(): YinYang = yinYang
}