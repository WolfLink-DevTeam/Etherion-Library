package org.wolflink.etherion.api.enums.date

import org.wolflink.etherion.api.entities.timeunits.SolarMDH
import java.io.BufferedReader
import java.io.FileReader

enum class SolarTerm(val chineseName: String) {
    LiChun("立春"),
    YuShui("雨水"),
    JingZhe("惊蛰"),
    ChunFen("春分"),
    QingMing("清明"),
    GuYu("谷雨"),
    LiXia("立夏"),
    XiaoMan("小满"),
    MangZhong("芒种"),
    XiaZhi("夏至"),
    XiaoShu("小暑"),
    DaShu("大暑"),
    LiQiu("立秋"),
    ChuShu("处暑"),
    BaiLu("白露"),
    QiuFen("秋分"),
    HanLu("寒露"),
    ShuangJiang("霜降"),
    LiDong("立冬"),
    XiaoXue("小雪"),
    DaXue("大雪"),
    DongZhi("冬至"),
    XiaoHan("小寒"),
    DaHan("大寒")
    ;

    fun getSolarMDH(year: Int): SolarMDH {
        val jieqi = this.chineseName
        if (year < 1900 || year > 2100) {
            throw IllegalArgumentException("year out of range, must be between 1900 and 2100")
        }
        val fileName = "src/assets/jieqi_data/$year.json"
        val dataStr = file2String(fileName)
        val dataMap = dataStr.substring(1, dataStr.length - 1).split(",").associate {
            val (key, value) = it.split(":")
            key.trim(' ', '"') to value.trim(' ', '"') + "时"
        }
        val month = dataMap[jieqi]?.substring("年", "月")?.toInt()
        val day = dataMap[jieqi]?.substring("月", "日")?.toInt()
        val hour = dataMap[jieqi]?.substring(" ", "时")?.toInt()
        if (month == null || day == null || hour == null) {
            throw IllegalArgumentException("invalid jieqi name")
        }
        return SolarMDH(month, day, hour)
    }
}


private fun file2String(fileName: String): String {
    val reader = BufferedReader(FileReader(fileName))
    val sb = StringBuilder()
    var line: String? = null
    while (reader.readLine().also { line = it } != null) {
        sb.append(line)
    }
    reader.close()
    return sb.toString()
}

private fun String.substring(start: String, end: String): String {
    val startIndex = this.indexOf(start) + 1
    val endIndex = this.indexOf(end)
    return this.substring(startIndex, endIndex)
}
