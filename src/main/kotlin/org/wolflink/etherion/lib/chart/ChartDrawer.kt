package org.wolflink.etherion.lib.chart

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.icepear.echarts.Line
import org.icepear.echarts.charts.line.LineSeries
import org.icepear.echarts.components.coord.cartesian.CategoryAxis
import org.icepear.echarts.render.Engine

object ChartDrawer {
    /**
     * @param title     图表标题
     * @param jsonArray 数据(每组数据第一列作为分组名)
     * @param keyName   第一组的主键名
     */
    fun drawStackedLine(title: String,jsonArray: JsonArray,keyName: String) {
        var maxGroupSize = 0
        var maxJsonObject = JsonObject()
        for (je in jsonArray) {
            if(je.isJsonObject) {
                val jo = je.asJsonObject
                if(maxGroupSize < jo.size()) {
                    maxGroupSize = jo.size()
                    maxJsonObject = jo
                }
            }
        }
        maxGroupSize--
        if(maxGroupSize <= 0) throw IllegalArgumentException("图表绘制数据长度过短")
        val xAxisDatas = arrayOfNulls<String>(maxGroupSize)
        var i = 0
        for (entry in maxJsonObject.asMap().entries) {
            if(entry.key == keyName) continue
            xAxisDatas[i] = entry.key
            i++
        }
        val lines = Line()
            .setTitle(title)
            .setTooltip("axis")
            .addXAxis(CategoryAxis().setBoundaryGap(false).setData(xAxisDatas))
            .addYAxis()
        for (je in jsonArray) {
            if(je.isJsonObject) {
                val jo = je.asJsonObject
                val numbers = arrayOfNulls<Number>(jo.size()-1)
                var i = 0
                for (entry in jo.asMap()) {
                    if(entry.key != keyName) {
                        numbers[i++] = entry.value.asNumber
                    }
                }
                lines.addSeries(LineSeries()
                    .setName(jo.get(keyName).asString)
                    .setData(numbers)
                )
            }
        }
        Engine().render("chart.html",lines)
    }

}


