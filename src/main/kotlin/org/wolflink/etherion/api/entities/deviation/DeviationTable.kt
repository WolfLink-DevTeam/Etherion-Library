package org.wolflink.etherion.api.entities.deviation

/**
 * 偏差表
 * 用于分析八字/紫薇盘数据
 */
data class DeviationTable(val set : MutableSet<DeviationRecord> = mutableSetOf()) : MutableSet<DeviationRecord> by set {
    /**
     * 获取总偏差权重
     */
    val deviationWeight : Int
        get() {
            return if(set.isEmpty()) 0
            else set.map { it.weight }.reduce{ a, b -> a+b }
        }
    /**
     * 获取偏差信息
     */
    val deviationInfo
        get() = set.map { it.info }
}
