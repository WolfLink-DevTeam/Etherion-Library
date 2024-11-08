package org.wolflink.etherion.lib.enums.date

/**
 * 非闰年月份与天数对照表
 */
enum class Month(val dayCount: Int) {
    Month1(31), Month2(28), Month3(31),
    Month4(30), Month5(31), Month6(30),
    Month7(31), Month8(31), Month9(30),
    Month10(31), Month11(30), Month12(31);

    companion object {
        fun getDays(year: Int, month: Int, day: Int): Int {
            var result = day
            for (i in 0 until month-1) {
                result += entries[i].dayCount
            }
            // 闰年
            if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                if(month > 2) return result + 1
            }
            return result
        }
    }
}