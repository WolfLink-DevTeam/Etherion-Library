package org.wolflink.etherion.lib.entities.bazi

import org.wolflink.etherion.lib.entities.bazi.packs.AbstractWords
import org.wolflink.etherion.lib.enums.base.DiZhi
import kotlin.math.absoluteValue

/**
 * 八字关系对象
 * 包含：干支六合，地支三合，地支半合，地支三会，地支六冲，地支三刑，地支相刑，地支相害，地支破
 */
class BaZiRelation {
    fun clearCache() {
        sixCombine.clear()
        threeCombine.clear()
        halfCombine.clear()
        threeMeet.clear()
        sixConflict.clear()
        diZhiThreeTorture.clear()
        diZhiTorture.clear()
        diZhiHurt.clear()
        diZhiDestroy.clear()
    }
    fun <T : AbstractWords> updateBy(words: T) {
        clearCache()
        // 天干六合检测
        for (main in 0 until words.size step 2)
        {
            for (i in main+2 until words.size step 2)
            {
                val deltaIndex = (words[main].ordinal - words[i].ordinal).absoluteValue
                if(deltaIndex == 5)sixCombine.add(main to i)
            }
        }
        // 地支六合检测
        for (main in 1 until words.size step 2)
        {
            for (i in main+2 until words.size step 2)
            {
                val totalIndex = words[main].ordinal + words[i].ordinal
                if(totalIndex == 1 || totalIndex == 13)sixCombine.add(main to i)
            }
        }
        // 地支三合检测 0/4/8 1/5/9 2/6/10 3/7/11
        for (first in 1 until words.size step 2)
        {
            for (second in first+2 until words.size step 2)
            {
                for (third in second+2 until words.size step 2)
                {
                    val firstDiZhi = words[first]
                    val secondDiZhi = words[second]
                    val thirdDiZhi = words[third]
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
        val temp1 = listOf(DiZhi.Zi, DiZhi.Wu, DiZhi.Mao, DiZhi.You)
        for(first in 1 until words.size step 2)
        {
            out@for(second in first+2 until words.size step 2)
            {
                val firstDiZhi = words[first]
                val secondDiZhi = words[second]
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
        for (first in 1 until words.size step 2)
        {
            for(second in first+2 until words.size step 2)
            {
                for(third in second+2 until words.size step 2)
                {
                    val firstDiZhi = words[first]
                    val secondDiZhi = words[second]
                    val thirdDiZhi = words[third]
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
        for (first in 1 until words.size step 2)
        {
            for (second in first+2 until words.size step 2)
            {
                val firstDiZhi = words[first]
                val secondDiZhi = words[second]
                val deltaIndex = (firstDiZhi.ordinal - secondDiZhi.ordinal).absoluteValue
                if(deltaIndex == 6)sixConflict.add(first to second)
            }
        }
        //三刑
        for (first in 1 until words.size step 2)
        {
            for(second in first+2 until words.size step 2)
            {
                for(third in second+2 until words.size step 2)
                {
                    val firstDiZhi = words[first]
                    val secondDiZhi = words[second]
                    val thirdDiZhi = words[third]
                    if(firstDiZhi == secondDiZhi || secondDiZhi == thirdDiZhi || firstDiZhi == thirdDiZhi)continue
                    val list = mutableListOf(firstDiZhi.ordinal,secondDiZhi.ordinal,thirdDiZhi.ordinal)
                    list.sort()
                    // 寅巳申
                    if(list[0] == 2 && list[1] == 5 && list[2] == 8)diZhiThreeTorture.add(Triple(first,second,third))
                    // 丑未戌
                    if(list[0] == 1 && list[1] == 7 && list[2] == 10)diZhiThreeTorture.add(Triple(first,second,third))
                }
            }
        }
        //相刑
        for (first in 1 until words.size step 2)
        {
            for(second in first+2 until words.size step 2)
            {
                val firstDiZhi = words[first]
                val secondDiZhi = words[second]
                val list = mutableListOf(firstDiZhi.ordinal,secondDiZhi.ordinal)
                list.sort()
                if(list[0] == 0 && list[1] == 3) diZhiTorture.add(first to second)
                if(list[0] == 4 && list[1] == 4) diZhiTorture.add(first to second)
                if(list[0] == 6 && list[1] == 6) diZhiTorture.add(first to second)
                if(list[0] == 9 && list[1] == 9) diZhiTorture.add(first to second)
                if(list[0] == 11 && list[1] == 11) diZhiTorture.add(first to second)
            }
        }
        //相害
        for (first in 1 until words.size step 2)
        {
            for(second in first+2 until words.size step 2)
            {
                val firstDiZhi = words[first]
                val secondDiZhi = words[second]
                val list = mutableListOf(firstDiZhi.ordinal,secondDiZhi.ordinal)
                list.sort()
                if(list[0] == 2 && list[1] == 5) diZhiHurt.add(first to second)
                if(list[0] == 3 && list[1] == 4) diZhiHurt.add(first to second)
                if(list[0] == 9 && list[1] == 10) diZhiHurt.add(first to second)
                if(list[0] == 8 && list[1] == 11) diZhiHurt.add(first to second)
                if(list[0] == 1 && list[1] == 6) diZhiHurt.add(first to second)
                if(list[0] == 0 && list[1] == 7) diZhiHurt.add(first to second)
            }
        }
        //相破
        for (first in 1 until words.size step 2)
        {
            for(second in first+2 until words.size step 2)
            {
                val firstDiZhi = words[first]
                val secondDiZhi = words[second]
                val list = mutableListOf(firstDiZhi.ordinal,secondDiZhi.ordinal)
                list.sort()
                if(list[0] == 0 && list[1] == 9) diZhiDestroy.add(first to second)
                if(list[0] == 2 && list[1] == 11) diZhiDestroy.add(first to second)
                if(list[0] == 3 && list[1] == 6) diZhiDestroy.add(first to second)
                if(list[0] == 4 && list[1] == 1) diZhiDestroy.add(first to second)
                if(list[0] == 5 && list[1] == 8) diZhiDestroy.add(first to second)
                if(list[0] == 7 && list[1] == 10) diZhiDestroy.add(first to second)
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
    // 地支三刑
    private val diZhiThreeTorture : MutableSet<Triple<Int,Int,Int>> = mutableSetOf()
    // 地支相刑
    private val diZhiTorture : MutableSet<Pair<Int,Int>> = mutableSetOf()
    // 地支相害
    private val diZhiHurt : MutableSet<Pair<Int,Int>> = mutableSetOf()
    // 地支破
    private val diZhiDestroy : MutableSet<Pair<Int,Int>> = mutableSetOf()


    fun show()
    {
        println("六合 $sixCombine")
        println("三合 $threeCombine")
        println("半合 $halfCombine")
        println("三会 $threeMeet")
        println("六冲 $sixConflict")
        println("三刑 $diZhiThreeTorture")
        println("相刑 $diZhiTorture")
        println("相害 $diZhiHurt")
        println("相破 $diZhiDestroy")
    }

    /**
     * 获取六合元素索引
     */
    fun getSixCombine(): Set<Pair<Int, Int>> = sixCombine

    /**
     * 获取三合元素索引
     */
    fun getThreeCombine(): Set<Triple<Int, Int, Int>> = threeCombine

    /**
     * 获取地支半合索引
     */
    fun getHalfCombine(): Set<Pair<Int, Int>> = halfCombine

    /**
     * 获取地支三会方局索引
     */
    fun getThreeMeet(): Set<Triple<Int, Int, Int>> = threeMeet

    /**
     * 获取地支六冲索引
     */
    fun getSixConflict(): Set<Pair<Int, Int>> = sixConflict

    /**
     * 获取地支三刑索引
     */
    fun getDiZhiThreeTorture(): Set<Triple<Int, Int, Int>> = diZhiThreeTorture

    /**
     * 获取地支相刑索引
     */
    fun getDiZhiTorture(): Set<Pair<Int, Int>> = diZhiTorture

    /**
     * 地支相害
     */
    fun getDiZhiHurt(): Set<Pair<Int, Int>> = diZhiHurt

    /**
     * 地支破
     */
    fun getDiZhiDestroy(): Set<Pair<Int, Int>> = diZhiDestroy

}