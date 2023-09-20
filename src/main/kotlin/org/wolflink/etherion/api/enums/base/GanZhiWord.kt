package org.wolflink.etherion.api.enums.base

import org.wolflink.etherion.api.enums.bazi.WuXing
import kotlin.enums.EnumEntries

interface GanZhiWord {

    val entries: EnumEntries<*>
    val ordinal: Int

    fun last(index: Int):GanZhiWord {
        return entries[(ordinal - index) % entries.size + entries.size] as GanZhiWord
    }
    fun next(index: Int):GanZhiWord {
        return entries[(ordinal + index) % entries.size] as GanZhiWord
    }
    fun getChineseName(): Char

    /**
     * 获取五行属性
     * 权重比 6 3 1
     */
    fun getWuXing(): Triple<WuXing, WuXing, WuXing>
    fun getYinYang(): YinYang
}