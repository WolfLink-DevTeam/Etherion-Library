package org.wolflink.etherion.api
import org.wolflink.etherion.api.entities.bazi.DynamicBaZi
import org.wolflink.etherion.api.entities.calendars.FateCalendar
import org.wolflink.etherion.api.entities.calendars.SolarCalendar
import org.wolflink.etherion.api.enums.base.DiZhi
import org.wolflink.etherion.api.enums.base.TianGan
import org.wolflink.etherion.api.enums.date.SolarTerm
import java.io.InputStreamReader
import java.util.*

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
//        val calendar = Calendar.getInstance()
//        calendar.set(2003,2,7,5,40)
//        DynamicBaZi(fateCalendar = FateCalendar(SolarCalendar(calendar,120.0,false)), queryCalendar = Calendar.getInstance()).apply {
//            println(luckStartHours)
//        }
        val tianGan = DiZhi.Chen
        println(tianGan.chineseName)
        println(tianGan.next(13).getChineseName())
        println(tianGan.last(13).getChineseName())
    }
}