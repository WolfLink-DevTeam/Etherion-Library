package org.wolflink.etherion.api.expansions

import org.wolflink.etherion.api.enums.date.SolarTerm
import java.text.SimpleDateFormat
import java.util.*

/**
 * 查询给定日期下一个节气
 */
fun Calendar.lastSolarTerm() = SolarTerm.lastSolarTerm(this)

/**
 * 查询给定日期下一个节气
 */
fun Calendar.nextSolarTerm() = SolarTerm.nextSolarTerm(this)
fun Calendar.inRange(start: Calendar,end: Calendar): Boolean {
    return if (start > end) this >= start || this <= end
    else (this in start..end)
}
fun Calendar.show() = println(SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.time))
/**
 * 毫秒转小时
 */
fun Long.millsToHours(): Double = this / 3600000.0