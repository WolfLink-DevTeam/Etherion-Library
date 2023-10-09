package org.wolflink.etherion.lib.entities.bazi.relations

import org.wolflink.etherion.lib.entities.bazi.packs.AbstractWords

interface IBaZiRelation {

    fun clearCache()
    fun <T: AbstractWords> updateBy(words: T)
    /**
     * 获取六合元素索引
     */
    fun getSixCombine(): Set<Pair<Int,Int>>
    /**
     * 获取三合元素索引
     */
    fun getThreeCombine(): Set<Triple<Int,Int,Int>>
    /**
     * 获取地支半合索引
      */
    fun getHalfCombine(): Set<Pair<Int,Int>>
    /**
     * 获取地支三会方局索引
     */
    fun getThreeMeet(): Set<Triple<Int,Int,Int>>

    /**
     * 获取地支六冲索引
     */
    fun getSixConflict(): Set<Pair<Int,Int>>

    /**
     * 获取地支三刑索引
     */
    fun getDiZhiThreeTorture(): Set<Triple<Int,Int,Int>>

    /**
     * 获取地支相刑索引
     */
    fun getDiZhiTorture(): Set<Pair<Int,Int>>
    /**
     * 地支相害索引
      */
    fun getDiZhiHurt(): Set<Pair<Int,Int>>
    /**
     * 地支破索引
      */
    fun getDiZhiDestroy(): Set<Pair<Int,Int>>
}