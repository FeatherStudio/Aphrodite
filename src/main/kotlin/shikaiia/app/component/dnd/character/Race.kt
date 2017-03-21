package shikaiia.app.component.dnd.character

import shikaiia.app.component.dnd.resources.*

open class BaseRace(var desc: String = "",
                    var adjust: AttributeSet = AttributeSet(),
                    var speed: Int = 25,
                    var skill: SkillList = emptySkill,
                    var freePoint: Int = 0)

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
