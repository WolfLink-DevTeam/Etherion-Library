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
        calendar.set(2002,10,21,20,45)
        val staticBaZi = StaticBaZi(fateCalendar = FateCalendar(SolarCalendar(calendar,120.0,false)))
        staticBaZi.show()
        DynamicBaZi(staticBaZi).apply {
            println((words as TwelveWords).extraFourWords!!)
            queryYear = 2085
            println((words as TwelveWords).extraFourWords!!)
        }

    }
}