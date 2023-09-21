package org.wolflink.etherion.api
import org.wolflink.etherion.api.entities.bazi.DynamicBaZi
import org.wolflink.etherion.api.entities.bazi.StaticBaZi
import org.wolflink.etherion.api.entities.bazi.packs.TwelveWords
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
        val calendar = Calendar.getInstance()
        calendar.set(2000,11,25,15,18)
        val staticBaZi = StaticBaZi(fateCalendar = FateCalendar(SolarCalendar(calendar,120.0,false)))
        println(staticBaZi.getWangShuaiValue())
        DynamicBaZi(staticBaZi).apply {
            println(getWangShuaiValue())
            println((words as TwelveWords))
            queryYear = 2085
            println((words as TwelveWords))
        }

    }
}