package shikaiia.app.component.dnd.character

import shikaiia.app.component.dnd.core.Dice
import shikaiia.app.component.dnd.core.randomTo

open class BaseCharacter(val race: BaseRace,
                         val characterClass: BaseCharacterClass,
                         val level: Int,
                         val ability: BaseCharacterAttribute) {

}

open class BaseCharacterAttribute() {
    constructor(attr: IntArray) : this() {
        this.attrList = attr
    }

    private var attrList: IntArray = intArrayOf(10, 10, 10, 10, 10, 10)
    // todo adjust list
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

    fun total() = attrList.sum()
    fun getAttrList() = attrList
    fun getByIndex(i: Int) = attrList[i]
    fun getByType(s: String) = attrList[getIndexByAttrType(s)]

    fun lowerAll(v: Int): BaseCharacterAttribute {
        applyAll { minus(v) }
        return this
    }

    fun applyAll(func: Int.() -> Int) {
        attrList = attrList.map { it -> it.func() }.toIntArray()
    }

    fun apply(index: Int, func: Int.() -> Int) {
        attrList[index] = attrList[index].func()
    }

    fun apply(type: String, func: Int.() -> Int) {
        val index = getIndexByAttrType(type)
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

    // 调整值
    fun modifier(index: Int): Int = (attrList[index] - 10) / 2

    fun modifier(type: String): Int = (attrList[getIndexByAttrType(type)] - 10) / 2

    fun getIndexByAttrType(name: String): Int {
        return when (name) {
            "Str" -> str
            "Dex" -> dex
            "Con" -> con
            "Int" -> int
            "Wis" -> wis
            "Cha" -> cha
            else -> -1
        }
    }

    operator fun plus(other: BaseCharacterAttribute): BaseCharacterAttribute {
        val new = BaseCharacterAttribute()
        new.attrList = this.attrList.mapIndexed { index, it -> it + other.attrList[index] }.toIntArray()
        return new
    }

    fun getConsumeByIndex(i: Int): Int {
        return getConsumeByValue(attrList[i])
    }

    fun getConsumeByValue(v: Int): Int {
        return when (v) {
            in 0..14 -> v
            in 15..16 -> 14 + (v - 14) * 2
            in 17..20 -> 18 + (v - 16) * 3
            else -> 30 + (v - 20) * 4
        }
    }

    fun totalConsume(): Int {
        return attrList.fold(0, { acc, i -> acc + getConsumeByValue(i) })
    }

}

fun randomPointCharacter(): BaseCharacterAttribute {
    return randomPointCharacter { randomTo(15) }
}

fun randomPointCharacter(range: () -> Int): BaseCharacterAttribute {
    val data = BaseCharacterAttribute()
    data.applyAll { range() }
    return data
}

fun randomFixedTotalPointCharacter(points: Int = 80): BaseCharacterAttribute {
    return randomFixedTotalPointCharacter(points, { randomTo(15) })
}

fun randomFixedTotalPointCharacter(points: Int = 80, randomFunc: () -> Int,
                                   base: BaseCharacterAttribute = ZeroAttrSet): BaseCharacterAttribute {
    var remain = points
    val data = base

    var validValue = listOf(0, 1, 2, 3, 4, 5)
    var dice = Dice(validValue)
    while (remain != 0) {
        val index = dice.dice()
        val tar = data.getByIndex(index)
        if (tar < 14) {
            if (remain >= 1) {
                data.apply(index, Int::inc)
                remain -= 1
            } else break
        } else if (tar in 14..15) {
            if (remain >= 2) {
                data.apply(index, Int::inc)
                remain -= 2
            } else {
                validValue = validValue.filter { it != index }
                dice = Dice(validValue)
            }
        } else if (tar > 16) {
            if (remain >= 3) {
                data.apply(index, Int::inc)
                remain -= 3
            } else {
                validValue = validValue.filter { it != index }
                dice = Dice(validValue)
            }
        }
    }
    return data
}

fun randomCharacter(points: Int = 32): BaseCharacterAttribute {
    val data = BaseCharacterAttribute()
    data.lowerAll(2)
    val adjust = randomFixedTotalPointCharacter(points, { randomTo(7) }, data)
    return adjust
}

val ZeroAttrSet: BaseCharacterAttribute = BaseCharacterAttribute().lowerAll(10)
