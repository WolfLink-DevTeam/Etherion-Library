package org.wolflink.etherion.api
import org.wolflink.etherion.api.entities.bazi.DynamicBaZi
import org.wolflink.etherion.api.entities.calendars.FateCalendar
import org.wolflink.etherion.api.entities.calendars.SolarCalendar
import org.wolflink.etherion.api.enums.date.SolarTerm
import java.io.InputStreamReader
import java.util.*

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        val calendar = Calendar.getInstance()
        calendar.set(2003,2,7,5,40)
        DynamicBaZi(fateCalendar = FateCalendar(SolarCalendar(calendar,120.0,false)), queryCalendar = Calendar.getInstance()).apply {
            println(luckStartHours)
        }
    }
}