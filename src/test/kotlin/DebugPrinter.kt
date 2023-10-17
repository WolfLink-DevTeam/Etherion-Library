import org.wolflink.etherion.lib.entities.smallsixren.SSRPalace
import org.wolflink.etherion.lib.entities.smallsixren.SmallSixRen
import org.wolflink.etherion.lib.expansions.forEach
import org.wolflink.etherion.lib.expansions.show


fun SmallSixRen.show() {
    println("占问时间")
    this.fateCalendar.solarCalendar.realCalendar.show()
    println("月建 ${this.monthDiZhi.chineseName} 时辰 ${this.hourDiZhi.chineseName} 占数 ${this.tripleNumber}")
    this.triplePalace.forEach { it.show() }
}
fun SSRPalace.show() {
    val format = "${this.type.chineseName}宫 ${this.palace.chineseName} ${this.palaceDiZhi.chineseName} ${this.wangShuai.chineseName} ${"%2s".format(this.changSheng.chineseName)} ${this.sixGod.chineseName} ${this.relative.chineseName}"
    println(format)
}