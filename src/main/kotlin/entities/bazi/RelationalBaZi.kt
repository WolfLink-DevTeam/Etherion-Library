package entities.bazi

import enums.base.DiZhi
import enums.base.TianGan
import kotlin.math.absoluteValue

/**
 * 关系化的八字盘，此类不包含流年大运等八字关系
 *
 * 包含天干地支之间生克合化的关系
 * 六合、三合、半合、三会、刑、冲、克、破
 */
class RelationalBaZi(val yearPillar : Pair<TianGan, DiZhi>,
                     val monthPillar : Pair<TianGan, DiZhi>,
                     val dayPillar : Pair<TianGan, DiZhi>,
                     val hourPillar : Pair<TianGan, DiZhi>){

    // 此处存放的干支六合坐标，0为年干，1为年支，2为月干，3为月支，4日干，5为日支，6为时干，7为时支
    val sixCombine : MutableList<Pair<Int,Int>> = mutableListOf()
    // 地支三合坐标
    val threeCombine : MutableList<Triple<Int,Int,Int>> = mutableListOf()
    // 地支半合
    val halfCombine : MutableList<Pair<Int,Int>> = mutableListOf()
    // 地支三会方局
    val threeMeet : MutableList<Pair<Int,Int>> = mutableListOf()
    // 地支六冲
    val sixConflict : MutableList<Pair<Int,Int>> = mutableListOf()
    // 地支相刑
    val diZhiTorture : MutableList<Pair<Int,Int>> = mutableListOf()
    // 地支相害
    val diZhiHurt : MutableList<Pair<Int,Int>> = mutableListOf()
    // 地支破
    val diZhiDestroy : MutableList<Pair<Int,Int>> = mutableListOf()

    init {
        // 天干六合检测
        for (main in 0..6 step 2)
        {
            for (i in main+2..6 step 2)
            {
                val deltaIndex = (mapIndex<TianGan>(main).ordinal - mapIndex<TianGan>(i).ordinal).absoluteValue
                if(deltaIndex == 5)sixCombine.add(main to i)
            }
        }
        // 地支六合检测
        for (main in 1..7 step 2)
        {
            for (i in main+2..7 step 2)
            {
                val totalIndex = mapIndex<DiZhi>(main).ordinal + mapIndex<DiZhi>(i).ordinal
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
                    val firstDiZhi = mapIndex<DiZhi>(first)
                    val secondDiZhi = mapIndex<DiZhi>(second)
                    val thirdDiZhi = mapIndex<DiZhi>(third)
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
                val firstDiZhi = mapIndex<DiZhi>(first)
                val secondDiZhi = mapIndex<DiZhi>(second)
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



    }

    private fun <T> mapIndex(input : Int) : T
    {
        return when(input)
        {
            0 -> yearPillar.first as T
            1 -> yearPillar.second as T
            2 -> monthPillar.first as T
            3 -> monthPillar.second as T
            4 -> dayPillar.first as T
            5 -> dayPillar.second as T
            6 -> hourPillar.first as T
            7 -> hourPillar.second as T
            else -> throw IllegalArgumentException("不合法的索引值：$input，期望：0~7")
        }
    }
    fun show()
    {
        println("六合 $sixCombine")
        println("三合 $threeCombine")
        println("半合 $halfCombine")
    }

}