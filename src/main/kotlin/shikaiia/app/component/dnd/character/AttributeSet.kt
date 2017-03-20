package shikaiia.app.component.dnd.character

import shikaiia.app.component.dnd.resources.*
import shikaiia.extension.public.noHigherThan

open class AttributeSet() {
    constructor(attr: IntArray) : this() {
        this.attrList = attr
    }

    constructor(str: Int = 0, dex: Int = 0, con: Int = 0, int: Int = 0, wis: Int = 0, cha: Int = 0) :
            this(intArrayOf(str, dex, con, int, wis, cha))

    var attrList: IntArray = intArrayOf(10, 10, 10, 10, 10, 10)
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
        //感知
        attrList[4] = value
    }
    var cha get() = attrList[5]; set(value) {
        attrList[5] = value
    }

    operator fun plus(other: AttributeSet): AttributeSet {
        val new = AttributeSet()
        new.attrList = this.attrList.mapIndexed { index, it -> it + other.attrList[index] }.toIntArray()
        return new
    }

}

open class BaseCharacterAttribute(val max: Int = 30) {
    constructor(attr: IntArray) : this() {
        this.attrSet = AttributeSet(attr)
    }

    private fun Int.lowerThanMax() = this.noHigherThan(max)

    var attrSet = AttributeSet()

    // todo adjust list
    var str get() = attrSet.attrList[0]; set(value) {
        attrSet.attrList[0] = value.lowerThanMax()
    }
    var dex get() = attrSet.attrList[1]; set(value) {
        attrSet.attrList[1] = value.lowerThanMax()
    }
    var con get() = attrSet.attrList[2]; set(value) {
        attrSet.attrList[2] = value.lowerThanMax()
    }
    var int get() = attrSet.attrList[3]; set(value) {
        attrSet.attrList[3] = value.lowerThanMax()
    }
    var wis get() = attrSet.attrList[4]; set(value) {
        attrSet.attrList[4] = value.lowerThanMax()
    }
    var cha get() = attrSet.attrList[5]; set(value) {
        attrSet.attrList[5] = value.lowerThanMax()
    }

    fun total() = attrSet.attrList.sum()
    fun getAttrList() = attrSet.attrList
    fun getByIndex(i: Int) = attrSet.attrList[i]
    fun getByType(s: String) = attrSet.attrList[getIndexByAttrType(s)]

    fun lowerAll(v: Int): BaseCharacterAttribute {
        applyAll { minus(v) }
        return this
    }

    fun applyAll(func: Int.() -> Int) {
        attrSet.attrList = attrSet.attrList.map { it -> it.func() }.toIntArray()
    }

    fun apply(index: Int, func: Int.() -> Int) {
        attrSet.attrList[index] = attrSet.attrList[index].func()
    }

    fun apply(type: String, func: Int.() -> Int) {
        val index = getIndexByAttrType(type)
        attrSet.attrList[index] = attrSet.attrList[index].func()
    }

    fun print() {
        println("""$Str: $str
$Dex: $dex
$Con: $con
$Inte: $int
$Wis: $wis
$Cha: $cha""")
    }

    // 调整值
    fun modifier(index: Int): Int = (attrSet.attrList[index] - 10) / 2

    fun modifier(type: String): Int = (attrSet.attrList[getIndexByAttrType(type)] - 10) / 2

    fun getIndexByAttrType(name: String): Int {
        return when (name) {
            Str -> str
            Dex -> dex
            Con -> con
            Inte -> int
            Wis -> wis
            Cha -> cha
            else -> -1
        }
    }

    fun getConsumeByIndex(i: Int): Int {
        return getConsumeByValue(attrSet.attrList[i])
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
        return attrSet.attrList.fold(0, { acc, i -> acc + getConsumeByValue(i) })
    }

}

open class BasePlayerAttribute : BaseCharacterAttribute(20)
