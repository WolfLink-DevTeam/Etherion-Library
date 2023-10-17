package org.wolflink.etherion.lib

import com.google.gson.JsonElement
import org.wolflink.etherion.lib.bazi.alg.BaZiAlgorithm
import org.wolflink.etherion.lib.entities.bazi.AbstractBaZi
import org.wolflink.etherion.lib.entities.bazi.DynamicBaZi
import org.wolflink.etherion.lib.entities.bazi.StaticBaZi
import org.wolflink.etherion.lib.entities.smallsixren.SmallSixRen
import org.wolflink.etherion.lib.enums.Gender

interface IEtherion {
    /**
     * 转换为静态八字盘
     *
     * @param name          命主姓名
     * @param gender        命主性别
     * @param birthplace    出生地
     * @param year          阳历年，如 2023 代表2023年
     * @param month         阳历月，如 1 代表1月
     * @param day           阳历日
     * @param hour          阳历时
     * @param min           阳历分
     * @param longitude     出生地经度，如114.5度，不要分秒
     * @return              静态八字盘
     */
    fun createStaticBaZi(
        name: String,
        gender: Gender,
        birthplace: String,
        year: Int,
        month: Int,
        day: Int,
        hour: Int,
        min: Int,
        longitude: Double
    ): StaticBaZi

    /**
     * 转换为动态八字盘
     *
     * @param staticBaZi    静态八字盘
     * @return              动态八字盘
     */
    fun createDynamicBaZi(staticBaZi: StaticBaZi): DynamicBaZi

    /**
     * 应用八字算法对八字进行计算/解析
     *
     * @param abstractBaZi  抽象八字盘
     * @param algorithm     八字算法
     * @param arguments     算法参数
     * @return              算法计算结果
     */
    fun applyAlgorithm(abstractBaZi: AbstractBaZi, algorithm: BaZiAlgorithm, vararg arguments: Any): JsonElement

    /**
     * 创建小六壬盘
     * @param year      起盘年份
     * @param month     起盘月份
     * @param day       起盘日期
     * @param hour      起盘小时
     * @param min       起盘分钟
     * @param numbers   占问数字
     */
    fun createSmallSixRen(
        year: Int,
        month: Int,
        day: Int,
        hour: Int,
        min: Int,
        numbers: Triple<Int,Int,Int>
    ): SmallSixRen
}