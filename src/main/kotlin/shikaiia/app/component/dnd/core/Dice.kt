package shikaiia.app.component.dnd.core

import shikaiia.app.component.dnd.character.Fighter
import shikaiia.app.component.dnd.resources.ClericName
import shikaiia.app.component.dnd.resources.FighterName
import shikaiia.app.component.dnd.resources.MageName
import shikaiia.app.component.dnd.resources.ThiefName
import shikaiia.extension.randomize

class Dice(val values: IntArray) {
    constructor(v: Int) : this(1, v)
    constructor(from: Int, to: Int) : this(from intTo to)
    constructor(range: IntRange) : this(range.toIntArray())
    constructor(list: List<Int>) : this(list.toIntArray())

    fun dice(): Int {
        return values.randomize()[0]
    }
}

fun IntRange.toIntArray(): IntArray {
    return this.map { it }.toIntArray()
}

infix fun Int.intTo(to: Int): IntArray {
    return (this..to).toIntArray()
}

val dice4 = Dice(4)
val dice6 = Dice(6)
val dice8 = Dice(8)
val dice10 = Dice(10)
val dice12 = Dice(12)
val dice20 = Dice(20)

fun d6() = dice6.dice()
fun d8() = dice8.dice()
fun d10() = dice10.dice()
fun d12() = dice12.dice()
fun d20() = dice20.dice()

fun randomTo(to: Int) = Dice(to).dice()
fun randomRange(from: Int, to: Int) = Dice(from..to).dice()

fun getHpDice(cls: String): Dice {
    return when (cls) {
        FighterName -> dice10
        MageName -> dice6
        ClericName -> dice8
        ThiefName -> dice6
        else -> dice4
    }
}
