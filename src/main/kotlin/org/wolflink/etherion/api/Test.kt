package org.wolflink.etherion.api
import org.wolflink.etherion.api.enums.date.SolarTerm
import java.io.InputStreamReader
import java.util.*

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        SolarTerm.BaiLu.getExactTime(2015).apply {
            println(this[Calendar.YEAR])
            println(this[Calendar.MONTH]+1)
            println(this[Calendar.DAY_OF_MONTH])
            println(this[Calendar.HOUR_OF_DAY])
            println(this[Calendar.MINUTE])
            println(this[Calendar.SECOND])
        }
    }
}