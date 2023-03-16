package entities.timeunits

/**
 * 真太阳时 - 月 日 时
 */
data class SolarMDH(val month : Int,val day : Int,val hour : Int)
{
    fun getTotalHours() : Int
    {
        // TODO 一个月不一定是30天
        return hour + day * 24 + month * 30 * 24
    }
    fun inRange(lastSolarMDH: SolarMDH, nextSolarMDH: SolarMDH) : Boolean
    {
        if(nextSolarMDH.getTotalHours() < lastSolarMDH.getTotalHours())
        {
            return getTotalHours() >= lastSolarMDH.getTotalHours() || getTotalHours() <= nextSolarMDH.getTotalHours()
        }
        return getTotalHours() in lastSolarMDH.getTotalHours()..nextSolarMDH.getTotalHours()
    }
    operator fun compareTo(another : SolarMDH) : Int
    {
        return getTotalHours() - another.getTotalHours()
    }
}