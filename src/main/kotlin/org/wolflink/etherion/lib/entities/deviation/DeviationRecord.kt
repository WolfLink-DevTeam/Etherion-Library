package org.wolflink.etherion.lib.entities.deviation

sealed class DeviationRecord(val info : String,val weight : Int) {
    data object LiChunDeviation : DeviationRecord("立春附近24小时内",20)
    data object SolarTermDeviation : DeviationRecord("节气附近24小时内",10)
    data object HourDeviation : DeviationRecord("临近时辰分界线5分钟以内",10)
}
