package enums.bazi

import enums.base.YinYang

enum class YinYangRelation {
    TongXing,YiXing;

    companion object{
        fun get(master : YinYang,another : YinYang) : YinYangRelation
        {
            return if(master != another) YiXing else TongXing
        }
    }
}