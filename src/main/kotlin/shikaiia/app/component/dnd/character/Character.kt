package shikaiia.app.component.dnd.character

import shikaiia.app.component.dnd.core.Dice
import shikaiia.app.component.dnd.core.randomTo
open class BaseCharacter(val race: BaseRace,
                         val characterClass: BaseCharacterClass,
                         val level: Int,
                         val ability: BaseCharacterAttribute){

    var proficiency = ProficiencySet()
}

open class BasePlayer(race: BaseRace,
                      characterClass: BaseCharacterClass,
                      level: Int,
                      ability: BasePlayerAttribute) : BaseCharacter(race, characterClass, level, ability)

fun randomPointCharacter(range: () -> Int = { randomTo(15) }): BaseCharacterAttribute {
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
