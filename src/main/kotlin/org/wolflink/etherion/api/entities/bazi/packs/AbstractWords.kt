package org.wolflink.etherion.api.entities.bazi.packs

import org.wolflink.etherion.api.enums.base.GanZhiWord

abstract class AbstractWords(
    val words: List<GanZhiWord>
): List<GanZhiWord> by words