package org.wolflink.etherion.api.utils

import java.io.InputStreamReader

object ResourceUtil {
    /**
     * 加载项目资源文件
     * @param path  资源文件相对路径，例如 data/SolarDeviation
     */
    fun loadResource(path: String): String {
        val inputStream = javaClass.classLoader.getResourceAsStream(path)
        var result = ""
        if (inputStream != null) {
            val reader = InputStreamReader(inputStream)
            result = reader.readText()
            reader.close()
            inputStream.close()
        }
        return result
    }
}