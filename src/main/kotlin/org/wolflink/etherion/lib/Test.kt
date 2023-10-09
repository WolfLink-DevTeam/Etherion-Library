package org.wolflink.etherion.lib
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.wolflink.etherion.lib.bazi.BaZiInterpreter
import org.wolflink.etherion.lib.entities.bazi.DynamicBaZi
import org.wolflink.etherion.lib.entities.bazi.StaticBaZi
import org.wolflink.etherion.lib.entities.calendars.FateCalendar
import org.wolflink.etherion.lib.entities.calendars.SolarCalendar
import java.io.InputStreamReader
import java.util.*

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        val calendar = Calendar.getInstance()
        calendar.set(2008,0,11,20,0)
        val staticBaZi = StaticBaZi(fateCalendar = FateCalendar(SolarCalendar(calendar,120.0,false)))
        BaZiInterpreter.overallEvaluation(staticBaZi)
        staticBaZi.show()
        DynamicBaZi(staticBaZi).apply {
            queryYear = 2010
            val ja = JsonArray()
            val help = JsonObject()
            val restraint = JsonObject()
            val delta = JsonObject()
            help.addProperty("年份","生助值")
            help.addProperty("年份","克泄值")
            help.addProperty("年份","旺衰值")
            for (i in 0..100) {
                val pair = getWangShuaiValue()
                help.addProperty("$queryYear",pair.first)
                restraint.addProperty("$queryYear",pair.second)
                delta.addProperty("$queryYear",pair.first - pair.second)
                queryYear++
            }
            ja.add(help)
            ja.add(restraint)
            ja.add(delta)
            println(ja)
        }

    }
}