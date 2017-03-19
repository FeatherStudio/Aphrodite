package shikaiia.app.component.dnd.character

import shikaiia.app.component.dnd.core.getHpDice

open class BaseCharacterClass(val name: String) {
    val hpDice = getHpDice(name)
}

class BaseCharacterClassAttributeAdjust(attr: IntArray) : BaseCharacterAttribute(attr)

val Fighter = BaseCharacterClass("Fighter")
val Mage = BaseCharacterClass("Mage")
val Cleric = BaseCharacterClass("Cleric")
val Thief = BaseCharacterClass("Thief")
