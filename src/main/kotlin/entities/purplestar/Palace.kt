package entities.purplestar

import enums.base.DiZhi
import enums.base.TianGan
import enums.purplestar.AStar
import enums.purplestar.BStar
import enums.purplestar.PalaceType
import enums.purplestar.PrimaryStar

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