package org.wolflink.etherion.api.entities.bazi.relations

import org.wolflink.etherion.api.entities.bazi.packs.AbstractWords

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
     * 获取地支相刑索引
     */
    fun getDiZhiTorture(): Set<Pair<Int,Int>>
    /**
     * 地支相害 ( 暂时不管 )
      */
    fun getDiZhiHurt(): Set<Pair<Int,Int>>
    /**
     * 地支破 ( 暂时不管 )
      */
    fun getDiZhiDestroy(): Set<Pair<Int,Int>>
}