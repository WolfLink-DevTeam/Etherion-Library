package org.wolflink.etherion.api.entities.bazi

import org.wolflink.etherion.api.entities.bazi.packs.BaZiPillar
import org.wolflink.etherion.api.entities.bazi.packs.TwelveWords
import org.wolflink.etherion.api.entities.bazi.relations.DynamicBaZiRelation
import org.wolflink.etherion.api.entities.calendars.FateCalendar
import org.wolflink.etherion.api.entities.deviation.DeviationTable
import org.wolflink.etherion.api.enums.Gender
import org.wolflink.etherion.api.enums.base.DiZhi
import org.wolflink.etherion.api.enums.base.TianGan
import org.wolflink.etherion.api.enums.bazi.WangShuai
import java.util.*

class DynamicBaZi(
    name: String = "",
    gender: Gender = Gender.MALE,
    birthplace: String = "",
    fateCalendar: FateCalendar,
    val queryCalendar: Calendar
) : AbstractBaZi(name, gender, birthplace, fateCalendar, DynamicBaZiRelation()) {
    // TODO 临时实现，请根据实际 queryCalendar 日期计算以获得，可以在 init 初始化块中完成相关任务
    val majorLuckPillar: BaZiPillar = BaZiPillar(master,TianGan.Bing to DiZhi.Chen)
    val minorLuckPillar: BaZiPillar = BaZiPillar(master,TianGan.Bing to DiZhi.Chen)

    override val words: TwelveWords =
        TwelveWords(yearPillar.pillar, monthPillar.pillar, dayPillar.pillar, hourPillar.pillar, majorLuckPillar.pillar, minorLuckPillar.pillar)

    /**
     * 获取当前日主旺衰的具体值
     * Pair<HelpValue,restrainValue>
     */
    override fun getWangShuaiValue(): Pair<Double, Double> {
        TODO("Not yet implemented")
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
    override fun checkDeviation(): DeviationTable {
        TODO("Not yet implemented")
    }

    /**
     * 展示命盘信息
     */
    override fun show() {
        TODO("Not yet implemented")
    }
}