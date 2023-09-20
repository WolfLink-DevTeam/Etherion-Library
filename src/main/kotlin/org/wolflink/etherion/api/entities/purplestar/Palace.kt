package org.wolflink.etherion.api.entities.purplestar

import org.wolflink.etherion.api.enums.base.DiZhi
import org.wolflink.etherion.api.enums.base.TianGan
import org.wolflink.etherion.api.enums.purplestar.AStar
import org.wolflink.etherion.api.enums.purplestar.BStar
import org.wolflink.etherion.api.enums.purplestar.PalaceType
import org.wolflink.etherion.api.enums.purplestar.PrimaryStar

// 宫位
class Palace(
    val palaceType: PalaceType,
    val tianGan: TianGan,
    val diZhi: DiZhi
) {
    val primaryStars = mutableListOf<PrimaryStar>()
    val aStars = mutableListOf<AStar>()
    val bStars = mutableListOf<BStar>()
}