package org.wolflink.etherion.lib.expansions


/**
 * 规格化，自动将数组控制到总和为1
 */
fun <T: Number> List<T>.normalize(): List<Double> {
    val doubleList = map { it.toDouble() }
    val total = doubleList.reduce { acc, t -> acc + t }
    return doubleList.map { it / total }
}