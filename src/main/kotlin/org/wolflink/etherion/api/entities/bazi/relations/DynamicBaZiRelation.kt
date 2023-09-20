package org.wolflink.etherion.api.entities.bazi.relations

import org.wolflink.etherion.api.entities.bazi.packs.AbstractWords

class DynamicBaZiRelation: IBaZiRelation {
    override fun clearCache() {
    }

    override fun <T : AbstractWords> updateBy(words: T) {
    }

    /**
     * 获取六合元素索引
     */
    override fun getSixCombine(): Set<Pair<Int, Int>> {
        return setOf()
    }

    /**
     * 获取三合元素索引
     */
    override fun getThreeCombine(): Set<Triple<Int, Int, Int>> {
        return setOf()
    }

    /**
     * 获取地支半合索引
     */
    override fun getHalfCombine(): Set<Pair<Int, Int>> {
        return setOf()
    }

    /**
     * 获取地支三会方局索引
     */
    override fun getThreeMeet(): Set<Triple<Int, Int, Int>> {
        return setOf()
    }

    /**
     * 获取地支六冲索引
     */
    override fun getSixConflict(): Set<Pair<Int, Int>> {
        return setOf()
    }

    /**
     * 获取地支相刑索引
     */
    override fun getDiZhiTorture(): Set<Pair<Int, Int>> {
        return setOf()
    }

    /**
     * 地支相害 ( 暂时不管 )
     */
    override fun getDiZhiHurt(): Set<Pair<Int, Int>> {
        return setOf()
    }

    /**
     * 地支破 ( 暂时不管 )
     */
    override fun getDiZhiDestroy(): Set<Pair<Int, Int>> {
        return setOf()
    }
}