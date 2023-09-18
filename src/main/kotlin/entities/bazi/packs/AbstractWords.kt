package entities.bazi.packs

import enums.base.GanZhiWord

abstract class AbstractWords(
    val words: List<GanZhiWord>
): List<GanZhiWord> by words