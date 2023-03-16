package entities.bazi

import enums.base.DiZhi
import enums.bazi.ShiShen
import enums.base.TianGan
import enums.bazi.ShiErChangSheng

class BaZiPillar(master : TianGan, val pillar: Pair<TianGan, DiZhi>) {
    // 主星
    val primaryStar : ShiShen
    // 副星 从左往右：6 3 1
    val accessoryStars : Triple<ShiShen,ShiShen,ShiShen>
    // 星运
    val xingYun : ShiErChangSheng
    // 自坐
    val ziZuo : ShiErChangSheng


    init {
        primaryStar = ShiShen.get(master,pillar.first)
        accessoryStars = Triple(
            ShiShen.get(master,pillar.second.nativeTianGan),
            ShiShen.get(master,pillar.second.middleTianGan),
            ShiShen.get(master,pillar.second.remnantTianGan))
        xingYun = ShiErChangSheng.get(master,pillar.second)
        ziZuo = ShiErChangSheng.get(pillar.first,pillar.second)
    }

}