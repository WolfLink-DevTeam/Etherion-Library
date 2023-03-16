package enums.base

enum class DiZhi(val chineseName : Char, val nativeTianGan: TianGan, val middleTianGan: TianGan, val remnantTianGan: TianGan) {
    Zi('子', TianGan.Gui, TianGan.Gui, TianGan.Gui),
    Chou('丑', TianGan.Ji, TianGan.Gui, TianGan.Xin),
    Yin('寅', TianGan.Jia, TianGan.Bing, TianGan.Wu),
    Mao('卯', TianGan.Yi, TianGan.Yi, TianGan.Yi),
    Chen('辰', TianGan.Wu, TianGan.Yi, TianGan.Gui),
    Si('巳', TianGan.Bing, TianGan.Geng, TianGan.Wu),
    Wu('午', TianGan.Ding, TianGan.Ji, TianGan.Ding),
    Wei('未', TianGan.Ji, TianGan.Ding, TianGan.Yi),
    Shen('申', TianGan.Geng, TianGan.Ren, TianGan.Wu),
    You('酉', TianGan.Xin, TianGan.Xin, TianGan.Xin),
    Xu('戌', TianGan.Wu, TianGan.Xin, TianGan.Ding),
    Hai('亥', TianGan.Ren, TianGan.Jia, TianGan.Ren)
}