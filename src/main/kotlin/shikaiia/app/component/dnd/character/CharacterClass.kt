package shikaiia.app.component.dnd.character

import shikaiia.app.component.dnd.core.getHpDice
import shikaiia.app.component.dnd.resources.ClericName
import shikaiia.app.component.dnd.resources.FighterName
import shikaiia.app.component.dnd.resources.MageName
import shikaiia.app.component.dnd.resources.ThiefName

typealias SaveSet = AttributeSet

open class BaseCharacterClass(val name: String, var adjust: AttributeSet = ZeroAttrSet,
                              var proficiency: ProficiencySet = ProficiencySet(),
                              var equip: EquipmentSet = EquipmentSet(),
                              var save: SaveSet = SaveSet()) {
    val hpDice = getHpDice(name)
    fun firstLevelHp(attr: AttributeSet) = attr.con
    fun levelUpHp(attr: AttributeSet) = hpDice.dice() + attr.con.modifier()

}

class BaseCharacterClassAttributeAdjust(attr: IntArray) : BaseCharacterAttribute(attr)

val Fighter = BaseCharacterClass(FighterName)
val Mage = BaseCharacterClass(MageName)
val Cleric = BaseCharacterClass(ClericName)
val Thief = BaseCharacterClass(ThiefName)

open class BaseFightingStyle
open class BaseSkillList
open class BaseMartialArchetype
