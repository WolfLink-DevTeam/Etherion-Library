package org.wolflink.etherion.api

import entities.bazi.StaticBaZi
import entities.calendars.FateCalendar
import entities.calendars.SolarCalendar
import enums.Gender
import java.util.*
/**
 * @param year          阳历年，如 2023 代表2023年
 * @param month         阳历月，如 1 代表1月
 * @param day           阳历日
 * @param hour          阳历时
 * @param min           阳历分
 * @param longitude     出生地经度，如114.5度，不要分秒
 * @param baZi          八字，用于纠正结果
 * @param needDeviation 是否需要微调真太阳时
 */
data class TestBaZi(val year : Int,val month : Int,val day : Int,val hour : Int,val min : Int,val longitude : Double,val baZi : String,val needDeviation : Boolean = true)
{
    private val baseBaZi : StaticBaZi
    init {
        val calendar = Calendar.getInstance()
        // 这里的month 0代表1月
        calendar.set(year,month-1,day,hour,min)
        val solarCalendar = SolarCalendar(calendar,longitude,needDeviation)
        val fateCalendar = FateCalendar(solarCalendar)
        baseBaZi = fateCalendar.toDetailBaZi("无名", Gender.MALE,"未知地")
    }

    companion object{
        var total = 0
        var success = 0
        var failed = 0

        val detailList = mutableListOf<String>()

        fun showResult()
        {
            println("""
                总共 $total
                成功 $success
                失败 $failed
            """.trimIndent())
        }
        fun showDetails()
        {
            val detail = "%-4s | %-2s | %-2s | %-2s | %-2s | %-5s | %s"
            val detailStr = String.format(detail,"Year","Mo","Da","ho","mi","longi","result")
            println(detailStr)
            for (detail in detailList) println(detail)
        }
    }

    fun test()
    {
        total++
        val result = testBaZi()
        if(result.first) success++
        else failed++
        val detail = "%-4s | %-2s | %-2s | %-2s | %-2s | %-5s | %s"
        val detailStr = String.format(detail,year,month,day,hour,min,longitude,result.second)
        detailList.add(detailStr)

        val deviationTable = baseBaZi.checkDeviation()
        if(deviationTable.deviationWeight > 0) {
            detailList.add("↑ 该八字有大约 ${deviationTable.deviationWeight}% 的概率存在偏差，原因：${deviationTable.deviationInfo}")
        }
    }
    // 批量测试八字
    fun testBaZi() : Pair<Boolean,String>
    {


        val yearColumn = baseBaZi.yearPillar.pillar
        val monthColumn = baseBaZi.monthPillar.pillar
        val dayColumn = baseBaZi.dayPillar.pillar
        val hourColumn = baseBaZi.hourPillar.pillar

        baseBaZi.show()

        if(baZi[0] != yearColumn.first.chineseName)return false to "年天干不匹配。结果：${yearColumn.first.chineseName} 预期：${baZi[0]}"
        if(baZi[1] != yearColumn.second.chineseName)return false to "年地支不匹配。结果：${yearColumn.second.chineseName} 预期：${baZi[1]}"

        if(baZi[2] != monthColumn.first.chineseName)return false to "月天干不匹配。结果：${monthColumn.first.chineseName} 预期：${baZi[2]}"
        if(baZi[3] != monthColumn.second.chineseName)return false to "月地支不匹配。结果：${monthColumn.second.chineseName} 预期：${baZi[3]}"

        if(baZi[4] != dayColumn.first.chineseName)return false to "日天干不匹配。结果：${dayColumn.first.chineseName} 预期：${baZi[4]}"
        if(baZi[5] != dayColumn.second.chineseName)return false to "日地支不匹配。结果：${dayColumn.second.chineseName} 预期：${baZi[5]}"

        if(baZi[6] != hourColumn.first.chineseName)return false to "时天干不匹配。结果：${hourColumn.first.chineseName} 预期：${baZi[6]}"
        if(baZi[7] != hourColumn.second.chineseName)return false to "时地支不匹配。结果：${hourColumn.second.chineseName} 预期：${baZi[7]}"
        return true to "通过"
    }
}
