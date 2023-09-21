import org.wolflink.etherion.api.entities.calendars.FateCalendar
import org.wolflink.etherion.api.entities.calendars.SolarCalendar
import org.wolflink.etherion.api.entities.purplestar.TianBoard
import org.wolflink.etherion.api.enums.Gender
import org.wolflink.etherion.api.enums.base.DiZhi
import org.wolflink.etherion.api.enums.base.TianGan
import org.wolflink.etherion.api.enums.bazi.ShiErChangSheng
import org.wolflink.etherion.api.enums.bazi.ShiShens
import org.wolflink.etherion.api.enums.date.SolarTerm
import org.wolflink.etherion.api.expansions.show
import java.util.*


fun main()
{
//    println(SolarTerm.BaiLu.getExactTime(2015).timeInMillis)
    testBazi()
//    testPurpleStar(2004,3,14,0,20)
}

fun testPurpleStar(year : Int,month : Int,date : Int,hour : Int,minute : Int)
{
    val calendar = Calendar.getInstance()
    // 这里的month 0代表1月
    calendar.set(year,month-1,date,hour,minute)
    println("生辰 ${year}年${month}月${date}日 $hour:$minute")
    val board = TianBoard("匿名",Gender.MALE,"未知", FateCalendar(SolarCalendar(calendar,120.0,false)))
    println("命宫索引 ${board.mingPalaceIndex}")
    println("命历月 ${board.fateCalendar.fateMonth}")
    for (palace in board.twelvePalaces) println("""
        ${palace.palaceType.chineseName} ${palace.tianGan.chineseName} ${palace.diZhi.chineseName}
    """.trimIndent())
}

fun testShiShen()
{
    for (first in TianGan.values())
        for (second in TianGan.values())
        {
            println("${first.chineseName} <- ${second.chineseName} : ${ShiShens.get(first,second).chineseName}")
        }
}
fun testChangSheng()
{
    for (gan in TianGan.values())
    {
        for (zhi in DiZhi.values())
        {
            println("${gan.chineseName} ${zhi.chineseName} ${ShiErChangSheng.get(gan,zhi).chineseName}")
        }
    }
}
fun testBazi()
{
    val testList = listOf(
        /**
         * 成功数据
         */
        TestBaZi(2002, 12, 19, 15, 50, 108.7, "壬午壬子辛酉丙申",Gender.MALE,false),
        TestBaZi(1979, 6, 9, 0, 30, 108.7, "己未庚午丁未庚子",Gender.MALE,false),
        TestBaZi(2006, 2, 5, 22, 59, 120.0, "丙戌庚寅乙丑丁亥", Gender.MALE,false),
        TestBaZi(2000, 8, 30, 7, 55, 120.0, "庚辰甲申庚申庚辰", Gender.MALE,false),
        TestBaZi(2003, 8, 18, 15, 4, 120.0, "癸未庚申癸亥庚申",Gender.MALE, false),
        TestBaZi(2005, 7, 19, 15, 2, 120.0, "乙酉癸未甲辰壬申",Gender.MALE, false),
        TestBaZi(2005, 1, 25, 2, 43, 120.0, "甲申丁丑己酉乙丑",Gender.MALE, false),
        TestBaZi(2002, 12, 20, 2, 46, 120.0, "壬午壬子壬戌辛丑", Gender.MALE,false),
        TestBaZi(2002, 12, 20, 9, 20, 120.0, "壬午壬子壬戌乙巳", Gender.MALE,false),
        TestBaZi(1997, 12, 15, 2, 0, 120.0, "丁丑壬子辛卯己丑", Gender.MALE,false),
        TestBaZi(1995, 1, 1, 23, 30, 120.0, "甲戌丙子癸巳壬子",Gender.MALE, false),
        TestBaZi(1975, 1, 1, 0, 0, 120.0, "甲寅丙子丁未庚子", Gender.MALE,false),
        TestBaZi(1975, 1, 1, 23, 59, 120.0, "甲寅丙子戊申壬子",Gender.MALE, false),
        TestBaZi(2002, 1, 20, 17, 0, 120.0, "辛巳辛丑戊子辛酉",Gender.MALE, false),
        TestBaZi(1975, 2, 4, 19, 0, 120.0, "乙卯戊寅辛巳戊戌", Gender.MALE,false),
        TestBaZi(2008, 1, 11, 3, 0, 120.0, "丁亥癸丑庚戌戊寅",Gender.MALE, false),
        TestBaZi(2004, 5, 5, 2, 30, 120.0, "甲申戊辰甲申乙丑", Gender.MALE,false),
        TestBaZi(1975, 2, 4, 19, 0, 120.0, "乙卯戊寅辛巳戊戌", Gender.MALE,false),
        // 壬午年十月初一，以目前的算法为9月末，但八字恰好合上了...紫微盘是错误的
        TestBaZi(2002, 11, 5, 6, 0, 120.0, "壬午庚戌丁丑癸卯", Gender.MALE,false),
        // 这组数据时间与节气当天重合，在看日柱的时候存在1天的偏差，时柱可能也受到了影响
        TestBaZi(2007, 7, 1, 1, 17, 120.0, "丁亥丙午丙申己丑",Gender.MALE, false),
        TestBaZi(1976, 1, 10, 23, 0, 120.0, "乙卯己丑壬戌庚子", Gender.FEMALE,false),
        TestBaZi(1977, 2, 11, 0, 0, 120.0, "丁巳壬寅己亥甲子", Gender.FEMALE,false),
        /**
         * 失败数据
         */
        /**
         * 等待测试的数据
         */
        TestBaZi(1978, 3, 12, 6, 0, 120.0, "戊午乙卯癸酉乙卯", Gender.FEMALE,false),
        TestBaZi(1979, 4, 13, 9, 0, 120.0, "己未戊辰庚戌辛巳", Gender.FEMALE,false),
    )
    for (test in testList)
    {
        test.test()
    }
    TestBaZi.showDetails()
    TestBaZi.showResult()
}



