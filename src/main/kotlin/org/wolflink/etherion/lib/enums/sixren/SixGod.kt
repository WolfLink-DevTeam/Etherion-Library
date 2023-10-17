package org.wolflink.etherion.lib.enums.sixren

enum class SixGod(val chineseName: String) {
    QingLong("青龙"),
    ZhuQue("朱雀"),
    GouChen("勾陈"),
    BaiHu("白虎"),
    TengShe("螣蛇"),
    XuanWu("玄武"),
    ;

    fun next(index: Int) = entries[(ordinal + index) % entries.size]
}