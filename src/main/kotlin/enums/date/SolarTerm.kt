package enums.date

import entities.timeunits.SolarMDH

/**
 * @param date 真太阳时 月-日-时
 *
 * 立春 索引值 0 月份 1
 * 惊蛰 索引值 1 月份 2
 * ......
 */
enum class SolarTerm(val chineseName: String, val solarMDH: SolarMDH) {
    LiChun("立春", SolarMDH(2, 4, 12)),
    JingZhe("惊蛰", SolarMDH(3, 6, 0)),
    QingMing("清明", SolarMDH(4, 5, 12)),
    LiXia("立夏", SolarMDH(5, 6, 12)),
    MangZhong("芒种", SolarMDH(6, 6, 12)),
    XiaoShu("小暑", SolarMDH(7, 7, 12)),
    LiQiu("立秋", SolarMDH(8, 8, 12)),
    BaiLu("白露", SolarMDH(9, 8, 12)),
    HanShuang("寒霜", SolarMDH(10, 8, 12)),
    LiDong("立冬", SolarMDH(11, 8, 0)),
    DaXue("大雪", SolarMDH(12, 7, 12)),
    XiaoHan("小寒", SolarMDH(1, 6, 12));
}
