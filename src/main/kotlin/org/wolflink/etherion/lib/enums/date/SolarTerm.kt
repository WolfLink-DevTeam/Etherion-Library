package org.wolflink.etherion.lib.enums.date

import org.wolflink.etherion.lib.entities.timeunits.SolarMDH
import org.wolflink.etherion.lib.dao.ResourceAccessor
import java.text.SimpleDateFormat
import java.util.*

/**
 * 节令索引能被2整除
 */
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

    companion object {
        /**
         * 查询节气(24节气)
         *
         * @param calendar  查询日期
         * @param reverse   是否逆向查找(是则为最近的上一个节气，否则为最近的下一个节气)
         */
        fun findNearestSolarTerm(calendar: Calendar,reverse: Boolean): Pair<SolarTerm,Long> {
            val year = calendar.get(Calendar.YEAR)
            var last: SolarTerm? = null
            var delta: Long = Long.MAX_VALUE
            for (solarTerm in entries) {
                val tempDelta =
                    if(reverse) calendar.timeInMillis - solarTerm.getExactTime(year).timeInMillis
                    else solarTerm.getExactTime(year).timeInMillis - calendar.timeInMillis
                if(tempDelta in 1..delta) {
                    delta = tempDelta
                    last = solarTerm
                }
            }
            if(last == null) {
                if(reverse) {
                    last = DongZhi
                    delta = calendar.timeInMillis - DongZhi.getExactTime(year-1).timeInMillis
                } else {
                    last = XiaoHan
                    delta = XiaoHan.getExactTime(year+1).timeInMillis - calendar.timeInMillis
                }
            }
            return last to delta
        }

        /**
         * 查询节令(12节气)
         *
         * @param calendar  查询日期
         * @param reverse   是否倒序查询
         */
        fun findNearestSolarSeason(calendar: Calendar,reverse: Boolean): Pair<SolarTerm,Long> {
            val pair1 = findNearestSolarTerm(calendar,reverse)
            // 是节令
            return if(pair1.first.ordinal % 2 == 0) pair1
            // 不是节令
            else {
                val pair2 = findNearestSolarTerm(pair1.first.getExactTime(calendar[Calendar.YEAR]),reverse)
                pair2.first to pair2.second + pair1.second
            }
        }
    }
    private val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
    private val exactTimeMap: Map<Int, Calendar>

    init {
        val dataText = ResourceAccessor.loadResource("data/solarterm/$name")
        if (dataText.isEmpty()) {
            println("节气 $chineseName 数据加载失败！")
            exactTimeMap = mutableMapOf()
        } else {
            exactTimeMap = dataText.split('\n')
                .filter { it.isNotEmpty() }.associate { line ->
                    val calendar = Calendar.getInstance()
                    calendar.time = format.parse(line)
                    calendar.get(Calendar.YEAR) to calendar
                }
        }
    }

    /**
     * 查询该节气在指定年份的准确日期
     */
    fun getExactTime(year: Int): Calendar = exactTimeMap[year]!!

    fun getSolarMDH(year: Int): SolarMDH {
        if (year < 1900 || year >= 2100) {
            throw IllegalArgumentException("year out of range, must be between 1900 and 2099")
        }
        val calendar = getExactTime(year)
        return SolarMDH(calendar[Calendar.MONTH]+1, calendar[Calendar.DAY_OF_MONTH], calendar[Calendar.HOUR_OF_DAY])
    }
}


//private fun file2String(fileName: String): String {
//    val reader = BufferedReader(FileReader(fileName))
//    val sb = StringBuilder()
//    var line: String? = null
//    while (reader.readLine().also { line = it } != null) {
//        sb.append(line)
//    }
//    reader.close()
//    return sb.toString()
//}
//
//private fun String.substring(start: String, end: String): String {
//    val startIndex = this.indexOf(start) + 1
//    val endIndex = this.indexOf(end)
//    return this.substring(startIndex, endIndex)
//}
