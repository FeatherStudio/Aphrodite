package shikaiia.extension

fun <T> Array<T>.randomize() : Array<T>{
    var currentIndex = this.size
    var tempValue : T
    var randomIndex : Int
    while(currentIndex != 0){
        randomIndex = Math.floor(Math.random() * currentIndex).toInt()
        currentIndex -= 1

        tempValue = this[currentIndex]
        this[currentIndex] = this[randomIndex]
        this[randomIndex] = tempValue
    }
    return this
}

fun IntArray.randomize() = this.toTypedArray().randomize()
