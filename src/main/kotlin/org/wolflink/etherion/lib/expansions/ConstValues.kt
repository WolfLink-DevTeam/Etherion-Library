package org.wolflink.etherion.lib.expansions

object ConstValues {

    /**
     * 旺衰权重系数，会自动规范化，总和为100
     * 10 12 0  12
     * 8  36 12 10
     */
    val wangShuaiValue8List = listOf(10,8,12,36,0,12,12,10).normalize()
    /**
     * 旺衰权重系数，会自动规范化，总和100为标准参考
     * 10  12  0   12  15  15
     * 8   40  14  10  15  15
     * 年  月  日   时  大运 流年
     */
    val wangShuaiValue12List = listOf(10, 8, 12, 40, 0, 14, 12, 10, 15, 15, 15, 15).normalize()

    /**
     * 五行权重系数，会自动规范化，总和100为标准参考
     * 8 8 12 8
     * 8 20 8 8
     */
    val wuXingValue8List = listOf(8,8,8,20,12,8,8,8).normalize()
    /**
     * 五行权重系数，会自动规范化，总和100为标准参考
     * 8  8  12  8   8   8
     * 8  20  8  8   8   8
     * 年 月 日  时 大运 流年
     */
    val wuXingValue12List = listOf(8,8,8,20,12,8,8,8,8,8,8,8).normalize()

    /**
     * 月令本中余力量比例
     */
    val monthMasterPowers = listOf(0.8,0.15,0.05)
    val triplePowers = listOf(0.6,0.3,0.1)

    /**
     * 规格化，自动将数组控制到总和为100
     */
    private fun <T: Number> List<T>.normalize(): List<Double> {
        val doubleList = map { it.toDouble() }
        val total = doubleList.reduce { acc, t -> acc + t }
        return doubleList.map { it / total * 100 }
    }
}