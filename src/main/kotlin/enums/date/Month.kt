package enums.date

/**
 * 非闰年月份与天数对照表
 */
enum class Month(val dayCount : Int) {
    Month1(31),Month2(28),Month3(31),Month4(30),Month5(31),Month6(30),
    Month7(31),Month8(31),Month9(30),Month10(31),Month11(30),Month12(31);

    companion object{
        fun getDays(year : Int,month : Int,day : Int) : Int
        {
            var result = 0
            result += day
            for (i in 0 until month-1)
            {
                result += values()[i].dayCount
            }
            if(year % 4 == 0 && year % 100 != 0)return result+1
            if(year % 400 == 0)return result+1
            return result
        }
    }
}