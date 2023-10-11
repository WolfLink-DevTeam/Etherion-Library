package org.wolflink.etherion.lib.entities.bazi.packs

import org.wolflink.etherion.lib.entities.bazi.AbstractBaZi
import org.wolflink.etherion.lib.expansions.normalize

/**
 * 量化八字类
 * 支持动态修改 words weight
 */
class QuantizationBaZi(val words: AbstractWords) {
    constructor(abstractBaZi: AbstractBaZi): this(abstractBaZi.words)
    val wangShuaiWeightList: List<Double>
    val wuXingWeightList: List<Double>
    init {
        when(words.size) {
            8 -> {
                wangShuaiWeightList = wangShuaiValue8List.toList()
                wuXingWeightList = wuXingValue8List.toList()
            }
            12 -> {
                wangShuaiWeightList = wuXingValue12List.toList()
                wuXingWeightList = wuXingValue12List.toList()
            }
            else -> throw IllegalArgumentException("不合法的 AbstractWords，大小为："+words.size)
        }
    }
}
/**
 * 旺衰权重系数，会自动规范化，总和为100
 * 10 12 0  12
 * 8  36 12 10
 */
private val wangShuaiValue8List = listOf(10,8,12,36,0,12,12,10).normalize()
/**
 * 旺衰权重系数，会自动规范化，总和100为标准参考
 * 10  12  0   12  15  15
 * 8   40  14  10  15  15
 * 年  月  日   时  大运 流年
 */
private val wangShuaiValue12List = listOf(10, 8, 12, 40, 0, 14, 12, 10, 15, 15, 15, 15).normalize()

/**
 * 五行权重系数，会自动规范化，总和100为标准参考
 * 8 8 12 8
 * 8 20 8 8
 */
private val wuXingValue8List = listOf(8,8,8,20,12,8,8,8).normalize()
/**
 * 五行权重系数，会自动规范化，总和100为标准参考
 * 8  8  12  8   8   8
 * 8  20  8  8   8   8
 * 年 月 日  时 大运 流年
 */
private val wuXingValue12List = listOf(8,8,8,20,12,8,8,8,8,8,8,8).normalize()

