package entities.bazi.relations

import entities.bazi.StaticBaZi
import enums.base.DiZhi
import kotlin.math.absoluteValue

/**
 * 关系化的八字盘，此类不包含流年大运等八字关系
 * 也不包含命主相关的其他信息如神煞、长生十二宫等
 *
 * 包含天干地支之间生克合化的关系
 * 六合、三合、半合、三会、刑、冲、克、破
 */
class StaticBaZiRelation: IBaZiRelation<StaticBaZi> {
    private fun clearCache() {
        sixCombine.clear()
        threeCombine.clear()
        halfCombine.clear()
        threeMeet.clear()
        sixConflict.clear()
        diZhiTorture.clear()
        diZhiHurt.clear()
        diZhiDestroy.clear()
    }
    override fun updateBy(baZi: StaticBaZi) {
        val eightWords = baZi.eightWords
        clearCache()
        // 天干六合检测
        for (main in 0..6 step 2)
        {
            for (i in main+2..6 step 2)
            {
                val deltaIndex = (eightWords[main].ordinal - eightWords[i].ordinal).absoluteValue
                if(deltaIndex == 5)sixCombine.add(main to i)
            }
        }
        // 地支六合检测
        for (main in 1..7 step 2)
        {
            for (i in main+2..7 step 2)
            {
                val totalIndex = eightWords[main].ordinal + eightWords[i].ordinal
                if(totalIndex == 1 || totalIndex == 13)sixCombine.add(main to i)
            }
        }
        // 地支三合检测 0/4/8 1/5/9 2/6/10 3/7/11
        for (first in 1..7 step 2)
        {
            for (second in first+2..7 step 2)
            {
                for (third in second+2..7 step 2)
                {
                    val firstDiZhi = eightWords[first]
                    val secondDiZhi = eightWords[second]
                    val thirdDiZhi = eightWords[third]
                    var delta1 = firstDiZhi.ordinal - secondDiZhi.ordinal
                    if(delta1 < 0)delta1 = 0 - delta1
                    if(delta1 > 4)delta1 -= 4
                    var delta2 = firstDiZhi.ordinal - thirdDiZhi.ordinal
                    if(delta2 < 0)delta2 = 0 - delta2
                    if(delta2 > 4)delta2 -= 4
                    var delta3 = secondDiZhi.ordinal - thirdDiZhi.ordinal
                    if(delta3 < 0)delta3 = 0 - delta3
                    if(delta3 > 4)delta3 -= 4
                    if(delta1 == 4 && delta2 == 4 && delta3 == 4) threeCombine.add(Triple(first,second,third))
                }
            }
        }
        // 地支半合，未形成三合，两个地支距离为4，并且其中有一个 子/午/卯/酉
        val temp1 = listOf(DiZhi.Zi,DiZhi.Wu,DiZhi.Mao,DiZhi.You)
        for(first in 1..7 step 2)
        {
            out@for(second in first+2..7 step 2)
            {
                val firstDiZhi = eightWords[first]
                val secondDiZhi = eightWords[second]
                var delta = (firstDiZhi.ordinal - secondDiZhi.ordinal).absoluteValue
                if(delta > 4)delta -= 4
                if(delta == 4 && (firstDiZhi in temp1 || secondDiZhi in temp1))
                {
                    // 排除参与三合的
                    for (temp in threeCombine)
                    {
                        val tempList = temp.toList()
                        if(first in tempList || second in tempList)continue@out
                    }
                    halfCombine.add(first to second)
                }
            }
        }
        // 数字范围 0~11
        // 三会方局 2/3/4 5/6/7 8/9/10 11/0/1
        for (first in 1..7 step 2)
        {
            for(second in first+2..7 step 2)
            {
                for(third in second+2..7 step 2)
                {
                    val firstDiZhi = eightWords[first]
                    val secondDiZhi = eightWords[second]
                    val thirdDiZhi = eightWords[third]
                    if(firstDiZhi == secondDiZhi || secondDiZhi == thirdDiZhi || firstDiZhi == thirdDiZhi)continue

                    val list = mutableListOf(firstDiZhi.ordinal,secondDiZhi.ordinal,thirdDiZhi.ordinal)
                    list.sort()
                    if(list[0] == 2 && list[1] == 3 && list[2] == 4)threeMeet.add(Triple(first,second,third))
                    if(list[0] == 5 && list[1] == 6 && list[2] == 7)threeMeet.add(Triple(first,second,third))
                    if(list[0] == 8 && list[1] == 9 && list[2] == 10)threeMeet.add(Triple(first,second,third))
                    if(list[0] == 0 && list[1] == 1 && list[2] == 11)threeMeet.add(Triple(first,second,third))
                }
            }
        }
        // 六冲
        for (first in 1..7 step 2)
        {
            for (second in first+2..7 step 2)
            {
                val firstDiZhi = eightWords[first]
                val secondDiZhi = eightWords[second]
                val deltaIndex = (firstDiZhi.ordinal - secondDiZhi.ordinal).absoluteValue
                if(deltaIndex == 6)sixConflict.add(first to second)
            }
        }
    }

    // 此处存放的干支六合坐标，0为年干，1为年支，2为月干，3为月支，4日干，5为日支，6为时干，7为时支
    private val sixCombine : MutableSet<Pair<Int,Int>> = mutableSetOf()
    // 地支三合坐标
    private val threeCombine : MutableSet<Triple<Int,Int,Int>> = mutableSetOf()
    // 地支半合
    private val halfCombine : MutableSet<Pair<Int,Int>> = mutableSetOf()
    // 地支三会方局
    private val threeMeet : MutableSet<Triple<Int,Int,Int>> = mutableSetOf()
    // 地支六冲
    private val sixConflict : MutableSet<Pair<Int,Int>> = mutableSetOf()
    // 地支相刑 ( 暂时不管 )
    private val diZhiTorture : MutableSet<Pair<Int,Int>> = mutableSetOf()
    // 地支相害 ( 暂时不管 )
    private val diZhiHurt : MutableSet<Pair<Int,Int>> = mutableSetOf()
    // 地支破 ( 暂时不管 )
    private val diZhiDestroy : MutableSet<Pair<Int,Int>> = mutableSetOf()


    fun show()
    {
        println("六合 $sixCombine")
        println("三合 $threeCombine")
        println("半合 $halfCombine")
        println("三会 $threeMeet")
        println()
    }

    /**
     * 获取六合元素索引
     */
    override fun getSixCombine(): Set<Pair<Int, Int>> = sixCombine

    /**
     * 获取三合元素索引
     */
    override fun getThreeCombine(): Set<Triple<Int, Int, Int>> = threeCombine

    /**
     * 获取地支半合索引
     */
    override fun getHalfCombine(): Set<Pair<Int, Int>> = halfCombine

    /**
     * 获取地支三会方局索引
     */
    override fun getThreeMeet(): Set<Triple<Int, Int, Int>> = threeMeet

    /**
     * 获取地支六冲索引
     */
    override fun getSixConflict(): Set<Pair<Int, Int>> = sixConflict

    /**
     * 获取地支相刑索引
     */
    override fun getDiZhiTorture(): Set<Pair<Int, Int>> = diZhiTorture

    /**
     * 地支相害 ( 暂时不管 )
     */
    override fun getDiZhiHurt(): Set<Pair<Int, Int>> = diZhiHurt

    /**
     * 地支破 ( 暂时不管 )
     */
    override fun getDiZhiDestroy(): Set<Pair<Int, Int>> = diZhiDestroy

}