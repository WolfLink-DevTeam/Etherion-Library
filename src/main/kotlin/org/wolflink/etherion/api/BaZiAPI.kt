package org.wolflink.etherion.api

import org.wolflink.etherion.api.entities.bazi.StaticBaZi
import org.wolflink.etherion.api.entities.calendars.FateCalendar
import org.wolflink.etherion.api.entities.calendars.SolarCalendar
import org.wolflink.etherion.api.enums.Gender
import java.util.*

/**
 * 八字相关接口实现
 */
class BaZiAPI {

    /**
     * @param name          命主姓名
     * @param gender        命主性别
     * @param birthplace    出生地
     * @param year          阳历年，如 2023 代表2023年
     * @param month         阳历月，如 1 代表1月
     * @param day           阳历日
     * @param hour          阳历时
     * @param min           阳历分
     * @param longitude     出生地经度，如114.5度，不要分秒
     */
    fun getBaZi(
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
        return fateCalendar.toDetailBaZi(name, gender,birthplace)
    }
}