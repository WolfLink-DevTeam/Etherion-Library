import org.wolflink.etherion.api.entities.bazi.packs.BaZiPillar
import org.wolflink.etherion.api.enums.bazi.WangShuai
import java.time.Month
import java.time.Year

class StandardBazi {
    lateinit var yearPillar: BaZiPillar
    lateinit var monthPillar: BaZiPillar
    lateinit var dayPillar: BaZiPillar
    lateinit var hourPillar: BaZiPillar
    lateinit var displayYear: Year
    lateinit var displayMonth: Month
    // 大运
    lateinit var majorLuckPillar: BaZiPillar
    // 流年
    lateinit var minorLuckPillar: BaZiPillar
    // 当前运势相对值
    var luckValue: Double = 0.0
    // 日主旺衰
    lateinit var wangShuai: WangShuai

    val sixCombine : MutableList<Pair<Int,Int>> = mutableListOf()
    // 地支三合坐标
    val threeCombine : MutableList<Triple<Int,Int,Int>> = mutableListOf()
    // 地支半合
    val halfCombine : MutableList<Pair<Int,Int>> = mutableListOf()
    // 地支三会方局
    val threeMeet : MutableList<Triple<Int,Int,Int>> = mutableListOf()
    // 地支六冲
    val sixConflict : MutableList<Pair<Int,Int>> = mutableListOf()
    // 地支相刑 ( 暂时不管 )
    val diZhiTorture : MutableList<Pair<Int,Int>> = mutableListOf()
    // 地支相害 ( 暂时不管 )
    val diZhiHurt : MutableList<Pair<Int,Int>> = mutableListOf()
    // 地支破 ( 暂时不管 )
    val diZhiDestroy : MutableList<Pair<Int,Int>> = mutableListOf()


}