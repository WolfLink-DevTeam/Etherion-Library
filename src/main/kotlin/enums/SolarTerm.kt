package enums

import entities.SolarMDH
import okhttp3.internal.notify

/**
 * @param date 真太阳时 月-日-时
 *
 * 立春 索引值 0 月份 1
 * 惊蛰 索引值 1 月份 2
 * ......
 */
enum class SolarTerm(val solarMDH : SolarMDH) {
    LiChun(SolarMDH(2,4,12)),
    JingZhe(SolarMDH(3,6,0)),
    QingMing(SolarMDH(4,5,12)),
    LiXia(SolarMDH(5,6,12)),
    MangZhong(SolarMDH(6,6,12)),
    XiaoShu(SolarMDH(7,7,12)),
    LiQiu(SolarMDH(8,8,12)),
    BaiLu(SolarMDH(9,8,12)),
    HanShuang(SolarMDH(10,8,12)),
    LiDong(SolarMDH(11,8,0)),
    DaXue(SolarMDH(12,7,12)),
    XiaoHan(SolarMDH(1,6,12));
}