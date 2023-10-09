package org.wolflink.etherion.lib.expansions

import org.wolflink.etherion.lib.enums.date.SolarTerm
import java.text.SimpleDateFormat
import java.util.*

fun Calendar.lastSolarTerm() = SolarTerm.findNearestSolarTerm(this,true)
fun Calendar.nextSolarTerm() = SolarTerm.findNearestSolarTerm(this,false)
fun Calendar.lastSolarSeason() = SolarTerm.findNearestSolarSeason(this,true)
fun Calendar.nextSolarSeason() = SolarTerm.findNearestSolarSeason(this,false)
fun Calendar.inRange(start: Calendar,end: Calendar): Boolean {
    return if (start > end) this >= start || this <= end
    else (this in start..end)
}
fun Calendar.show() = println(SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.time))
/**
 * 毫秒转小时
 */
fun Long.millsToHours(): Double = this / 3600000.0