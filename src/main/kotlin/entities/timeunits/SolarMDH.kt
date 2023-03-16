package entities.timeunits

/**
 * 真太阳时 - 月 日 时
 */
data class SolarMDH(val month: Int, val day: Int, val hour: Int) {
    fun inRange(start: SolarMDH, end: SolarMDH): Boolean {
        return if (start > end) this >= start || this <= end
        else (this >= start && this <= end)
    }

    operator fun compareTo(other: SolarMDH): Int {
        return when {
            this.month != other.month -> this.month - other.month
            this.day != other.day -> this.day - other.day
            else -> this.hour - other.hour
        }
    }

    fun getTotalHours(): Int {
        val daysInMonth = intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
        var totalHours = 0
        for (m in 1 until month) {
            totalHours += daysInMonth[m] * 24
        }
        totalHours += (day - 1) * 24 + hour
        return totalHours
    }
}
