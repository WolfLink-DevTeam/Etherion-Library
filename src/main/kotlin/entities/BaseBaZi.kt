package entities

import entities.calendars.SolarCalendar
import enums.DiZhi
import enums.Sex
import enums.TianGan

/**
 * 基础的八字信息
 * 包含以下参数：姓名，性别，出生地，真太阳时，年柱干支，月柱干支，日柱干支，时柱干支
 */

class BaseBaZi(val name : String,
               val sex : Sex,
               val birthplace : String,
               val solarCalendar: SolarCalendar,
               val yearPillar : Pair<TianGan,DiZhi>,
               val monthPillar : Pair<TianGan,DiZhi>,
               val dayPillar : Pair<TianGan,DiZhi>,
               val hourPillar : Pair<TianGan,DiZhi>, ) {

}