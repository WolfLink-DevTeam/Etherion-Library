package org.wolflink.etherion.api
import org.wolflink.etherion.api.enums.date.SolarTerm
import java.io.InputStreamReader
import java.util.*

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        val calendar = Calendar.getInstance()
        val lastTerm = SolarTerm.lastSolarTerm(calendar)
        val nextTerm = SolarTerm.nextSolarTerm(calendar)
        println(lastTerm.first.chineseName)
        println(nextTerm.first.chineseName)
    }
}