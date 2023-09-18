package entities.bazi.relations

import entities.bazi.DynamicBaZi
import entities.bazi.packs.AbstractWords
import entities.bazi.packs.TwelveWords
import enums.base.GanZhiWord

class DynamicBaZiRelation: IBaZiRelation {
    override fun clearCache() {
        TODO("Not yet implemented")
    }

    override fun <T : AbstractWords> updateBy(words: T) {
        TODO("Not yet implemented")
    }

    /**
     * 获取六合元素索引
     */
    override fun getSixCombine(): Set<Pair<Int, Int>> {
        TODO("Not yet implemented")
    }

    /**
     * 获取三合元素索引
     */
    override fun getThreeCombine(): Set<Triple<Int, Int, Int>> {
        TODO("Not yet implemented")
    }

    /**
     * 获取地支半合索引
     */
    override fun getHalfCombine(): Set<Pair<Int, Int>> {
        TODO("Not yet implemented")
    }

    /**
     * 获取地支三会方局索引
     */
    override fun getThreeMeet(): Set<Triple<Int, Int, Int>> {
        TODO("Not yet implemented")
    }

    /**
     * 获取地支六冲索引
     */
    override fun getSixConflict(): Set<Pair<Int, Int>> {
        TODO("Not yet implemented")
    }

    /**
     * 获取地支相刑索引
     */
    override fun getDiZhiTorture(): Set<Pair<Int, Int>> {
        TODO("Not yet implemented")
    }

    /**
     * 地支相害 ( 暂时不管 )
     */
    override fun getDiZhiHurt(): Set<Pair<Int, Int>> {
        TODO("Not yet implemented")
    }

    /**
     * 地支破 ( 暂时不管 )
     */
    override fun getDiZhiDestroy(): Set<Pair<Int, Int>> {
        TODO("Not yet implemented")
    }
}