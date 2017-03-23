package shikaiia.app.component.dnd.character

import shikaiia.app.component.dnd.resources.*

typealias BodySize = Int
const val BipedFine = 1 // 超微型
const val BipedDiminutive = 2 // 微型
const val BipedTiny = 4 // 超小型
const val BipedSmall = 6 // 小型
const val BipedMedium = 8 // 中型
const val BipedLarge = 16 // 大型
const val BipedHuge = 32 // 超大型
const val BipedGargantuan = 64 // 巨型
const val BipedColossal = 128 // 超巨型

const val QuadrupedConstant = 1.5

open class BaseRace(var desc: String = "",
                    var adjust: AttributeSet = AttributeSet(),
                    var speed: Int = 25,
                    var skill: SkillList = emptySkill,
                    var freePoint: Int = 0,
                    var size: BodySize = BipedMedium)

open class Dwarf : BaseRace(DwarfDesc, AttributeSet(con = 2))
class HillDwarf() : Dwarf() {
    init {
        desc = HillDwarfDesc
        adjust += AttributeSet(wis = 1)
    }
}

open class Elf : BaseRace(ElfDesc, AttributeSet(dex = 2), 30)
class HighElf : Elf() {
    init {
        desc = HighElfDesc
        adjust += AttributeSet(int = 1)
    }
}

open class Halfling : BaseRace(HalflingDesc, AttributeSet(dex = 2))
class Lightfoot : Halfling() {
    init {
        desc = LightfootDesc
        adjust += AttributeSet(cha = 1)
    }
}

class Human : BaseRace(HumanDesc, AttributeSet(1, 1, 1, 1, 1, 1), 30)

class Dragonborn : BaseRace(DragonbornDesc, AttributeSet(str = 2, cha = 1), 30)

open class Gnome : BaseRace(GnomeDesc, AttributeSet(int = 2))
class RockGnome : Gnome() {
    init {
        desc = RockGnomeDesc
        adjust = AttributeSet(con = 1)
    }
}

class HalfElf : BaseRace(HalfElfDesc, AttributeSet(cha = 1), 30, freePoint = 2)

class HalfOrc : BaseRace(HalfOrcDesc, AttributeSet(str = 2, con = 1), 30)

class Tiefling : BaseRace(TieflingDesc, AttributeSet(int = 1, cha = 2), 30)
