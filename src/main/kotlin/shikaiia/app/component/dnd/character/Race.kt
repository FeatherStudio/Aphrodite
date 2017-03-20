package shikaiia.app.component.dnd.character

open class BaseRace(val desc: String = "",
                    var adjust: AttributeSet = AttributeSet(),
                    var speed: Int = 25,
                    var skill: SkillList = emptySkill,
                    var freePoint: Int = 0)

open class Dwarf : BaseRace("", AttributeSet(con = 2))
class HillDwarf() : Dwarf() {
    init {
        adjust += AttributeSet(wis = 1)
    }
}

open class Elf : BaseRace("", AttributeSet(dex = 2), 30)
class HighElf : Elf() {
    init {
        adjust += AttributeSet(int = 1)
    }
}

open class Halfling : BaseRace("", AttributeSet(dex = 2))
class Lightfoot : Halfling() {
    init {
        adjust += AttributeSet(cha = 1)
    }
}

class Human : BaseRace("", AttributeSet(1, 1, 1, 1, 1, 1), 30)

class Dragonborn : BaseRace("", AttributeSet(str = 2, cha = 1), 30)

open class Gnome : BaseRace("", AttributeSet(int = 2))
class RockGnome : Gnome() {
    init {
        adjust = AttributeSet(con = 1)
    }
}

class HalfElf : BaseRace("", AttributeSet(cha = 1), 30, freePoint = 2)

class HalfOrc : BaseRace("", AttributeSet(str = 2, con = 1), 30)

class Tiefling : BaseRace("", AttributeSet(int = 1, cha = 2), 30)