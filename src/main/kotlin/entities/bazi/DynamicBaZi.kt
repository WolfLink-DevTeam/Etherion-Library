package entities.bazi

import entities.bazi.relations.DynamicBaZiRelation
import entities.bazi.relations.StaticBaZiRelation
import entities.calendars.FateCalendar
import entities.deviation.DeviationTable
import enums.Gender
import enums.bazi.WangShuai
import java.util.Calendar

class DynamicBaZi(
    name: String = "",
    gender: Gender = Gender.MALE,
    birthplace: String = "",
    fateCalendar: FateCalendar,
    val queryCalendar: Calendar
) : AbstractBaZi(name, gender, birthplace, fateCalendar, DynamicBaZiRelation()) {
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