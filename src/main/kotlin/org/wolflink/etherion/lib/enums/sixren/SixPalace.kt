package org.wolflink.etherion.lib.enums.sixren

import org.wolflink.etherion.lib.enums.base.DiZhi
import org.wolflink.etherion.lib.enums.base.YinYang

/**
 * 六宫
 *
 * @param chineseName   中文名称
 * @param yinYang       阴阳属性
 * @param yangDiZhi     阳地支
 * @param yinDiZhi      阴地支
 */
enum class SixPalace(val chineseName: String,val yinYang: YinYang,val yangDiZhi: DiZhi,val yinDiZhi: DiZhi) {
    DaAn("大安",YinYang.Yang,DiZhi.Yin,DiZhi.Mao),
    LiuLian("流连",YinYang.Yin,DiZhi.Xu,DiZhi.Wei),
    SuXi("速喜",YinYang.Yang,DiZhi.Wu,DiZhi.Si),
    ChiKou("赤口",YinYang.Yin,DiZhi.Shen,DiZhi.You),
    XiaoJi("小吉",YinYang.Yang,DiZhi.Zi,DiZhi.Hai),
    KongWang("空亡",YinYang.Yin,DiZhi.Chen,DiZhi.Chou);

    /**
     * 往后顺数 {index} 个宫位
     * 例如：大安顺数1为流连
     */
    fun next(index: Int) = entries[(index + ordinal) % entries.size]
}