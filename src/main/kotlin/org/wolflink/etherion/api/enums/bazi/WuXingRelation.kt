package org.wolflink.etherion.api.enums.bazi

import org.wolflink.etherion.api.enums.base.GanZhiWord

/**
 * @param chineseName   中文名称
 */
enum class WuXingRelation(val chineseName: String,val helpWeight: Double,val restrainWeight: Double) {
    ShengWo("生我",0.75,-0.25),
    WoSheng("我生",-0.25,-0.25),
    KeWo("克我",0.0,1.0),
    WoKe("我克",-0.5,+0.5),
    TongWo("同我",+1.0,0.0);
}
infix fun GanZhiWord.relationTo(another: GanZhiWord): Triple<WuXingRelation,WuXingRelation,WuXingRelation> {
    return Triple(
        this.getWuXing().first relationTo another.getWuXing().first,
        this.getWuXing().second relationTo another.getWuXing().second,
        this.getWuXing().third relationTo another.getWuXing().third
    )
}
infix fun WuXing.relationTo(another: WuXing): WuXingRelation {
    var deltaOrdinal = this.ordinal - another.ordinal
    if (deltaOrdinal >= 3) deltaOrdinal = 5 - deltaOrdinal
    if (deltaOrdinal <= -3) deltaOrdinal += 5
    return when (deltaOrdinal) {
        -2 -> WuXingRelation.WoKe
        -1 -> WuXingRelation.WoSheng
        0 -> WuXingRelation.TongWo
        1 -> WuXingRelation.ShengWo
        2 -> WuXingRelation.KeWo
        else -> throw IllegalStateException("不可能存在的情况")
    }
}
