package org.wolflink.etherion.api
import java.io.InputStreamReader

object Test {
    @JvmStatic
    fun main(args: Array<String>) {
        val inputStream = javaClass.classLoader.getResourceAsStream("data/SolarDeviation")
        if (inputStream != null) {
            val reader = InputStreamReader(inputStream)
            println(reader.readText())
        }
    }
}