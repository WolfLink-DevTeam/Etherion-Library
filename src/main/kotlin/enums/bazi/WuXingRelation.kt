package enums.bazi

import java.awt.font.MultipleMaster
import kotlin.math.absoluteValue

enum class WuXingRelation {
    ShengWo,WoSheng,KeWo,WoKe,TongWo;

    companion object{
        fun get(master : WuXing,another :WuXing) : WuXingRelation
        {
            var deltaOrdinal = master.ordinal - another.ordinal
            if(deltaOrdinal >= 3) deltaOrdinal = 5 - deltaOrdinal
            if(deltaOrdinal <= -3) deltaOrdinal += 5
            return when(deltaOrdinal)
            {
                -2 -> {
                    WoKe
                }
                -1 -> {
                    WoSheng
                }
                0 -> {
                    TongWo
                }
                1 -> {
                    ShengWo
                }
                2 -> {
                    KeWo
                }
                else -> {throw IllegalStateException("不可能存在的情况")}
            }
        }
    }
}