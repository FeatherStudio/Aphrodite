package shikaiia.app.component.dnd.character

import shikaiia.app.component.dnd.core.randomTo

open class BaseCharacter {


}

open class BaseCharacterAttribute {
    private var attrList = intArrayOf(10, 10, 10, 10, 10, 10)
    var str get() = attrList[0]; set(value) {
        attrList[0] = value
    }
    var dex get() = attrList[1]; set(value) {
        attrList[1] = value
    }
    var con get() = attrList[2]; set(value) {
        attrList[2] = value
    }
    var int get() = attrList[3]; set(value) {
        attrList[3] = value
    }
    var wis get() = attrList[4]; set(value) {
        attrList[4] = value
    }
    var cha get() = attrList[5]; set(value) {
        attrList[5] = value
    }

    fun total() = str + dex + con + int + wis + cha

    fun getAttrList() = attrList

    fun lowerAll(v: Int) {
        fun Int.lower(value: Int) = this - v
        changeAll { lower(v) }
    }

    fun changeAll(func: Int.() -> Int) {
        attrList = attrList.map { it -> it.func() }.toIntArray()
    }

    fun applyByIndex(index: Int, func: Int.() -> Int) {
        attrList[index] = attrList[index].func()
    }

    fun print() {
        println("""Str: $str
Dex: $dex
Con: $con
Int: $int
Wis: $wis
Cha: $cha""")
    }

    fun adjustByRace() {

    }

    fun adjustByClass() {

    }

    operator fun plus(other: BaseCharacterAttribute): BaseCharacterAttribute {
        val new = BaseCharacterAttribute()
        new.attrList = this.attrList.mapIndexed { index, it-> it+other.attrList[index] }.toIntArray()
        return new
    }
}

fun randomPointCharacter(): BaseCharacterAttribute {
    return randomPointCharacter { randomTo(15) }
}

fun randomPointCharacter(range: () -> Int): BaseCharacterAttribute {
    val data = BaseCharacterAttribute()
    data.str = range()
    data.dex = range()
    data.con = range()
    data.int = range()
    data.wis = range()
    data.cha = range()
    return data
}

fun randomFixedTotalPointCharacter(points: Int = 60): BaseCharacterAttribute {
    val data = randomPointCharacter()
    var delta = data.total() - points

    if (delta == 0) return data

    val div: Int = delta / 6
    delta -= 6 * div
    data.lowerAll(div)

    while (delta != 0) {
        val it = randomTo(6)
        fun Int.decr() = this - 1
        fun Int.incr() = this + 1
        data.applyByIndex(it - 1, if (delta > 0) Int::decr else Int::incr)
        delta += if (delta > 0) -1 else 1
    }

    return data
}

fun randomCharacter(points: Int = 30): BaseCharacterAttribute {
    val data = BaseCharacterAttribute()
    data.lowerAll(5)
    return data + randomFixedTotalPointCharacter(points)
}
