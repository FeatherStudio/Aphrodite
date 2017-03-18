package shikaiia.extension.public

fun <T, R> T.repeat(times: Int, block: T.() -> R){
    for(x in 1..times){
        block()
    }
}