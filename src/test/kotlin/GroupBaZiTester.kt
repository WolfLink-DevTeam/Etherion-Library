import org.wolflink.etherion.api.enums.Gender

/**
 * 八字分组测试对象
 */
class GroupBaZiTester(val groupName: String) {

    companion object {
        val simpleTemplate = "%-4s | %-2s | %-2s | %-2s | %-2s | %-5s | %s"
    }

    private val testBaZiList = mutableListOf<TestBaZi>()

    fun add(){}
    fun add(year : Int,month : Int,day : Int,hour : Int,min : Int, longitude : Double, baZi : String,gender: Gender = Gender.MALE, needDeviation : Boolean = true) {
        testBaZiList.add(TestBaZi(year,month,day,hour,min, longitude, baZi, gender, needDeviation))
    }

    fun test(detail: Boolean = false) {
        var success = 0
        var failed = 0
        println("[ $groupName 正在测试 ]")
        for (testBaZi in testBaZiList.withIndex()) {
            if(testBaZi.value.matchBaZi().first) success++
            else failed++
            if(detail) {
                println("第 ${testBaZi.index} 个")
                testBaZi.value.staticBaZi.show()
                println("------------------------------")
            }
        }
        println("""
            [ $groupName 测试结果 ]
            成功 $success 组
            失败 $failed 组
            合计 ${success+failed} 组
            ------------------------------
        """.trimIndent())
    }
}