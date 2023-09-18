package enums.base

import enums.bazi.WuXing

interface GanZhiWord {

    val ordinal: Int
    fun getChineseName(): Char

    /**
     * 获取五行属性
     * 权重比 6 3 1
     */
    fun getWuXing(): Triple<WuXing, WuXing, WuXing>
    fun getYinYang(): YinYang
}