package org.wolflink.etherion.lib.enums.sixren

import org.wolflink.etherion.lib.enums.base.WuXingRelation

enum class SixRelative(val chineseName: String) {
    ZiSun("子孙"),
    FuMu("父母"),
    GuanGui("官鬼"),
    XiongDi("兄弟"),
    QiCai("妻财");
    companion object {
        fun fromWuXingRelation(wuXingRelation: WuXingRelation): SixRelative {
            return when(wuXingRelation) {
                WuXingRelation.KeWo -> GuanGui
                WuXingRelation.ShengWo -> FuMu
                WuXingRelation.WoKe -> QiCai
                WuXingRelation.WoSheng -> ZiSun
                WuXingRelation.TongWo -> XiongDi
            }
        }
    }
}