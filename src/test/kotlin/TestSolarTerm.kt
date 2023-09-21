import org.wolflink.etherion.api.enums.date.SolarTerm

fun main() {
    println(SolarTerm.LiChun.getSolarMDH(2021))
    println(SolarTerm.LiChun.getSolarMDH(2022))
    println(SolarTerm.LiXia.getSolarMDH(2023))
}