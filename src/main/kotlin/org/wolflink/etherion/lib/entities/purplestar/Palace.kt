package org.wolflink.etherion.lib.entities.purplestar

import org.wolflink.etherion.lib.enums.base.DiZhi
import org.wolflink.etherion.lib.enums.base.TianGan
import org.wolflink.etherion.lib.enums.purplestar.AStar
import org.wolflink.etherion.lib.enums.purplestar.BStar
import org.wolflink.etherion.lib.enums.purplestar.PalaceType
import org.wolflink.etherion.lib.enums.purplestar.PrimaryStar

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