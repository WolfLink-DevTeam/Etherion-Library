package org.wolflink.etherion.api.enums.bazi

import org.wolflink.etherion.api.enums.base.YinYang

enum class YinYangRelation(val chineseName: String) {
    TongXing("同性"), YiXing("异性");

    companion object{
        fun get(master : YinYang, another : YinYang) : YinYangRelation
        {
            return if(master != another) YiXing else TongXing
        }
    }
}
