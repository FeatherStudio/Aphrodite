package shikaiia.extension.public

fun <T, R> T.repeat(times: Int, block: T.() -> R){
    for(x in 1..times){
        block()
    }
}

// Cannot cast Comparable<T> to T
// <T> Comparable<T>.noHigherThan(max: T): T
// cannot return this
infix fun Int.noHigherThan(max: Int): Int{
    if(this > max) return max
    else return this
}
