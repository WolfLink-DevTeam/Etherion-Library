package entities.bazi

import enums.base.DiZhi
import enums.base.TianGan
import enums.bazi.ShiErChangSheng
import enums.bazi.ShiShens

/**
 * 八字单柱信息
 * 包含该柱所属八字日主(用于推断主星副星星运等)
 */
class BaZiPillar(master : TianGan, val pillar: Pair<TianGan, DiZhi>) {
    var master : TianGan = master
    set(value) {
        field = value
        update()
    }
    // 主星
    lateinit var primaryStar : ShiShens
    // 副星 从左往右：6 3 1
    lateinit var accessoryStars : Triple<ShiShens,ShiShens,ShiShens>
    // 星运
    lateinit var xingYun : ShiErChangSheng
    // 自坐
    lateinit var ziZuo : ShiErChangSheng

    init {
        update()
    }
    /**
     * 在日主发生改变时调用该方法刷新主星副星星运等属性
     */
    private fun update() {
        primaryStar = ShiShens.get(master,pillar.first)
        accessoryStars = Triple(
            ShiShens.get(master,pillar.second.mixedWuXing.nativeTianGan),
            ShiShens.get(master,pillar.second.mixedWuXing.middleTianGan),
            ShiShens.get(master,pillar.second.mixedWuXing.remnantTianGan))
        xingYun = ShiErChangSheng.get(master,pillar.second)
        ziZuo = ShiErChangSheng.get(pillar.first,pillar.second)
    }
}