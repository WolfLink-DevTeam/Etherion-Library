package org.wolflink.etherion.lib

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import org.omg.CORBA.Object
import org.wolflink.etherion.lib.bazi.alg.BaZiAlgorithm
import org.wolflink.etherion.lib.entities.bazi.AbstractBaZi
import org.wolflink.etherion.lib.entities.bazi.DynamicBaZi
import org.wolflink.etherion.lib.entities.bazi.StaticBaZi
import org.wolflink.etherion.lib.entities.calendars.FateCalendar
import org.wolflink.etherion.lib.entities.calendars.SolarCalendar
import org.wolflink.etherion.lib.enums.Gender
import java.util.*

/**
 * 八字相关接口实现
 */
object EtherionImpl: IEtherion {

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
    override fun parseStaticBaZi(
        name: String,
        gender: Gender,
        birthplace: String,
        year: Int,
        month: Int,
        day: Int,
        hour: Int,
        min: Int,
        longitude: Double
    ): StaticBaZi {
        val calendar = Calendar.getInstance()
        calendar.set(year,month-1,day,hour,min)
        val solarCalendar = SolarCalendar(calendar,longitude,true)
        val fateCalendar = FateCalendar(solarCalendar)
        return fateCalendar.toStaticBaZi(name, gender,birthplace)
    }

    /**
     * 转换为动态八字盘
     *
     * @param staticBaZi    静态八字盘
     * @return              动态八字盘
     */
    override fun parseDynamicBaZi(staticBaZi: StaticBaZi): DynamicBaZi {
        return DynamicBaZi(staticBaZi)
    }

    /**
     * 应用八字算法对八字进行计算/解析
     *
     * @param abstractBaZi  抽象八字盘
     * @param algorithm     八字算法
     * @param arguments     算法参数(可空)
     * @return              算法计算结果
     */
    override fun applyAlgorithm(abstractBaZi: AbstractBaZi, algorithm: BaZiAlgorithm,vararg arguments: Any): JsonElement {
        return if(algorithm.checkInput(arguments)) {
            algorithm.compute(abstractBaZi,arguments)
        } else JsonObject()
    }

}