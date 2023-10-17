package org.wolflink.etherion.lib.expansions


fun <T> Pair<T,T>.forEach(block: (T) -> Unit) {
    first.run(block)
    second.run(block)
}
fun<T> Triple<T,T,T>.forEach(block: (T) -> Unit) {
    first.run(block)
    second.run(block)
    third.run(block)
}
fun <T,R> Pair<T,T>.map(block: (T) -> R):Pair<R,R> {
    return first.run(block) to second.run(block)
}
fun <T,R> Triple<T,T,T>.map(block: (T) -> R):Triple<R,R,R> {
    val newFirst = first.run(block)
    val newSecond = second.run(block)
    val newThird = third.run(block)
    return Triple(newFirst,newSecond,newThird)
}