package entities.bazi

import enums.base.DiZhi
import enums.base.TianGan
import enums.bazi.ShiErChangSheng
import enums.bazi.ShiShens

class BaZiPillar(master : TianGan, val pillar: Pair<TianGan, DiZhi>) {
    // 主星
    val primaryStar : ShiShens
    // 副星 从左往右：6 3 1
    val accessoryStars : Triple<ShiShens,ShiShens,ShiShens>
    // 星运
    val xingYun : ShiErChangSheng
    // 自坐
    val ziZuo : ShiErChangSheng

    init {
        primaryStar = ShiShens.get(master,pillar.first)
        accessoryStars = Triple(
            ShiShens.get(master,pillar.second.nativeTianGan),
            ShiShens.get(master,pillar.second.middleTianGan),
            ShiShens.get(master,pillar.second.remnantTianGan))
        xingYun = ShiErChangSheng.get(master,pillar.second)
        ziZuo = ShiErChangSheng.get(pillar.first,pillar.second)
    }

}