package enums.base

import enums.bazi.WuXing

enum class DiZhi(
    val chineseName: Char,
    val yinYang: YinYang,
    val nativeTianGan: TianGan,
    val middleTianGan: TianGan,
    val remnantTianGan: TianGan
) : GanZhiWord {
    Zi('子', YinYang.Yang, TianGan.Gui, TianGan.Gui, TianGan.Gui),
    Chou('丑', YinYang.Yin, TianGan.Ji, TianGan.Gui, TianGan.Xin),
    Yin('寅', YinYang.Yang, TianGan.Jia, TianGan.Bing, TianGan.Wu),
    Mao('卯', YinYang.Yin, TianGan.Yi, TianGan.Yi, TianGan.Yi),
    Chen('辰', YinYang.Yang, TianGan.Wu, TianGan.Yi, TianGan.Gui),
    Si('巳', YinYang.Yin, TianGan.Bing, TianGan.Geng, TianGan.Wu),
    Wu('午', YinYang.Yang, TianGan.Ding, TianGan.Ji, TianGan.Ding),
    Wei('未', YinYang.Yin, TianGan.Ji, TianGan.Ding, TianGan.Yi),
    Shen('申', YinYang.Yang, TianGan.Geng, TianGan.Ren, TianGan.Wu),
    You('酉', YinYang.Yin, TianGan.Xin, TianGan.Xin, TianGan.Xin),
    Xu('戌', YinYang.Yang, TianGan.Wu, TianGan.Xin, TianGan.Ding),
    Hai('亥', YinYang.Yin, TianGan.Ren, TianGan.Jia, TianGan.Ren);

    override fun getChineseName(): Char = chineseName
    override fun getWuXing(): Triple<WuXing, WuXing, WuXing> {
        return Triple(nativeTianGan.wuXing,middleTianGan.wuXing,remnantTianGan.wuXing)
    }
    override fun getYinYang(): YinYang = yinYang
}