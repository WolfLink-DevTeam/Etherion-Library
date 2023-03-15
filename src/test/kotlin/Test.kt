import configs.SolarDeviationConfig

fun main()
{
    SolarDeviationConfig.load()

    val testList = listOf(
        /**
         * 成功数据
         */
        TestBaZi(2002,12,19,15,50,108.7,"壬午壬子辛酉丙申"),
        TestBaZi(1979,6,9,1,30,108.7,"己未庚午丁未庚子"),
        TestBaZi(2006,2,5,22,59,120.0,"丙戌庚寅乙丑丁亥",false),
        TestBaZi(2004,5,5,2,30,120.0,"甲申戊辰甲申乙丑",false),
        TestBaZi(2000,8,30,7,55,120.0,"庚辰甲申庚申庚辰",false),
        TestBaZi(2007,7,1,1,17,120.0,"丁亥丙午丙申己丑",false),
        TestBaZi(2003,8,18,15,4,120.0,"癸未庚申癸亥庚申",false),
        TestBaZi(2005,7,19,15,2,120.0,"乙酉癸未甲辰壬申",false),
        TestBaZi(2005,1,25,2,43,120.0,"甲申丁丑己酉乙丑",false),
        TestBaZi(2002,12,20,2,46,120.0,"壬午壬子壬戌辛丑",false),
        TestBaZi(2002,12,20,9,20,120.0,"壬午壬子壬戌乙巳",false),
        TestBaZi(1997,12,15,2,0,120.0,"丁丑壬子辛卯己丑",false),
        /**
         * 失败数据
         */
        // 这组数据时间与节气当天重合，在看日柱的时候存在1天的偏差，时柱可能也受到了影响
        TestBaZi(2008,1,11,3,0,120.0,"丁亥癸丑庚戌戊寅",false),
        // 这组数据是上一组数据的纠正，算法没问题的
        TestBaZi(2008,1,10,3,0,120.0,"丁亥癸丑庚戌戊寅",false),
        /**
         * 等待测试的数据
         */
        TestBaZi(1995,1,1,23,30,120.0,"甲戌丙子癸巳壬子",false),
    )
    for (test in testList)
    {
        test.test()
    }
    TestBaZi.showResult()
    TestBaZi.showDetails()

    SolarDeviationConfig.save()

//    for (month in listOf(1,2,3,4,5,6,7,8,9,10,11,12))
//    {
//        val monthGanZhi = getMonthGanZhi(2023,month)
//        println("2023-$month ${monthGanZhi.first.chineseName} ${monthGanZhi.second.chineseName}")
//    }
}



