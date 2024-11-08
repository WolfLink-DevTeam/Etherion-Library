package org.wolflink.etherion.lib.entities.bazi

import org.wolflink.etherion.lib.bazi.BaZiCalculator
import org.wolflink.etherion.lib.entities.bazi.packs.BaZiPillar
import org.wolflink.etherion.lib.entities.bazi.packs.TwelveWords
import org.wolflink.etherion.lib.entities.calendars.FateCalendar
import org.wolflink.etherion.lib.entities.deviation.DeviationTable
import org.wolflink.etherion.lib.enums.Gender
import org.wolflink.etherion.lib.enums.base.DiZhi
import org.wolflink.etherion.lib.enums.base.TianGan
import org.wolflink.etherion.lib.enums.base.YinYang
import org.wolflink.etherion.lib.enums.base.WangShuai
import org.wolflink.etherion.lib.expansions.*
import java.util.*

class DynamicBaZi(
    val staticBaZi: StaticBaZi
) : AbstractBaZi(staticBaZi.name, staticBaZi.gender, staticBaZi.birthplace, staticBaZi.fateCalendar,TwelveWords(staticBaZi.fateCalendar)) {

    var queryYear: Int = Calendar.getInstance()[Calendar.YEAR]
        private set

    /**
     * 大运柱
     */
    lateinit var majorLuckPillar: BaZiPillar
        private set
    /**
     * 流年柱
     */
    lateinit var minorLuckPillar: BaZiPillar
        private set
    /**
     * 起运时间
     */
    val startLuckCalendar: Calendar

    /**
     * 更新动态八字盘的年份
     *
     * @param queryYear 年份
     */
    fun updateLuckPillars(queryYear: Int) {
        this.queryYear = queryYear
        val startYear = startLuckCalendar[Calendar.YEAR]
        val index = (queryYear - startYear) / 10 + 1
        majorLuckPillar = if(needReverse()) BaZiPillar(master,monthPillar.pillar.first.last(index) as TianGan to monthPillar.pillar.second.last(index) as DiZhi)
        else BaZiPillar(master,monthPillar.pillar.first.next(index) as TianGan to monthPillar.pillar.second.next(index) as DiZhi)
        minorLuckPillar = BaZiPillar(master,FateCalendar.getYearGanZhi(queryYear))
        (words as TwelveWords).updateLuckPillars(majorLuckPillar, minorLuckPillar)
    }

    /**
     * 起运时间(自出生起的小时数)
     */
    val luckStartHours: Int
    init {
        // 阴男阳女 倒排
        luckStartHours = if(needReverse()) {
            (120 * fateCalendar.solarCalendar.realCalendar.lastSolarSeason().second.millsToHours()).toInt()
        } else {
            (120 * fateCalendar.solarCalendar.realCalendar.nextSolarSeason().second.millsToHours()).toInt()
        }
        // 起运时间
        startLuckCalendar = fateCalendar.solarCalendar.realCalendar.clone() as Calendar
        startLuckCalendar.add(Calendar.HOUR,luckStartHours)

        updateLuckPillars(queryYear)
    }

    /**
     * 每次获取八字对象都会根据当前命盘动态创建一个对象
     */
    override fun getBaZiRelation(): BaZiRelation {
        val relation = BaZiRelation()
        relation.updateBy(words)
        return relation
    }

    /**
     * 获取当前日主旺衰的具体值
     * Pair<HelpValue,restrainValue>
     */
    override fun getWangShuaiValue(): Pair<Double, Double> {
        return BaZiCalculator.calcWangShuaiValue(words)
    }

    /**
     * 获取当前日主旺衰
     * 静态盘为固定值
     * 动态盘为动态计算值，与流年大运有关
     */
    override fun getWangShuai(): WangShuai {
        TODO("Not yet implemented")
    }

    /**
     * 检查四柱准确度详细信息
     *
     * 生辰时间是否在立春前后24小时内
     * 月日是否临近节气前后24小时内
     * 生辰历的时是否刚好处于单数时分界线附近30分钟
     *
     * 存偏概率 0 ~ 100
     * 0为绝对不可能有误，100为绝对存在偏差
     */
    override fun checkDeviation(): DeviationTable = staticBaZi.checkDeviation()

    /**
     * 展示命盘信息
     */
    override fun show() {
        TODO("Not yet implemented")
    }
    /**
     * 需要逆排大运流年
     */
    private fun needReverse(): Boolean {
        val yearTianGan = words[0]
        return !((gender == Gender.MALE && yearTianGan.getYinYang() == YinYang.Yang)
                || (gender == Gender.FEMALE && yearTianGan.getYinYang() == YinYang.Yin))
    }
}