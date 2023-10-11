package org.wolflink.etherion.lib.enums.base

import org.wolflink.etherion.lib.enums.bazi.MixedTianGan
import org.wolflink.etherion.lib.enums.bazi.WuXing
import kotlin.enums.EnumEntries

enum class DiZhi(
    @JvmField val chineseName: Char,
    @JvmField val yinYang: YinYang,
    @JvmField val mixedTianGan: MixedTianGan
) : GanZhiWord {
    Zi('子', YinYang.Yang,MixedTianGan(TianGan.Gui, TianGan.Gui, TianGan.Gui)),
    Chou('丑', YinYang.Yin, MixedTianGan(TianGan.Ji, TianGan.Gui, TianGan.Xin)),
    Yin('寅', YinYang.Yang, MixedTianGan(TianGan.Jia, TianGan.Bing, TianGan.Wu)),
    Mao('卯', YinYang.Yin, MixedTianGan(TianGan.Yi, TianGan.Yi, TianGan.Yi)),
    Chen('辰', YinYang.Yang, MixedTianGan(TianGan.Wu, TianGan.Yi, TianGan.Gui)),
    Si('巳', YinYang.Yin, MixedTianGan(TianGan.Bing, TianGan.Geng, TianGan.Wu)),
    Wu('午', YinYang.Yang, MixedTianGan(TianGan.Ding, TianGan.Ji, TianGan.Ding)),
    Wei('未', YinYang.Yin, MixedTianGan(TianGan.Ji, TianGan.Ding, TianGan.Yi)),
    Shen('申', YinYang.Yang, MixedTianGan(TianGan.Geng, TianGan.Ren, TianGan.Wu)),
    You('酉', YinYang.Yin, MixedTianGan(TianGan.Xin, TianGan.Xin, TianGan.Xin)),
    Xu('戌', YinYang.Yang, MixedTianGan(TianGan.Wu, TianGan.Xin, TianGan.Ding)),
    Hai('亥', YinYang.Yin, MixedTianGan(TianGan.Ren, TianGan.Jia, TianGan.Ren));

    override val iEntries: EnumEntries<*>
        get() = entries
    override fun getChineseName(): Char = chineseName
    override fun getWuXing(): Triple<WuXing, WuXing, WuXing> = mixedTianGan.getWuXing()
    override fun getYinYang(): YinYang = yinYang
}