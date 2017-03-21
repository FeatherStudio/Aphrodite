package shikaiia.app.component.dnd.character

import shikaiia.app.component.dnd.core.Dice
import shikaiia.app.component.dnd.core.d20
import shikaiia.app.component.dnd.core.randomTo

open class BaseCharacter(val race: BaseRace,
                         val characterClass: BaseCharacterClass,
                         val level: Int,
                         val ability: BaseCharacterAttribute) {
    fun getAbilityByName(name: String): Int {
        return ability.getByName(name)
    }
    fun getModifierByName(name: String): Int {
        return ability.modifier(name)
    }

    var proficiency = ProficiencySet()
}

open class BasePlayer(race: BaseRace, characterClass: BaseCharacterClass, level: Int = 1,
                      ability: BasePlayerAttribute) : BaseCharacter(race, characterClass, level, ability) {
    var abilityCheckAdjusts: Array<Pair<AttributeSet, String>> = emptyAdjust()
    var abilityCheckTimesAdjusts: TimesAdjusts = emptyAdjust()
    var saveCheckAdjusts: AttrAdjusts = emptyAdjust()
    var saveCheckTimesAdjusts: TimesAdjusts = emptyAdjust()
    var attackDiceAdjusts: AttrAdjusts = emptyAdjust()
    var attackDiceTimesAdjusts: TimesAdjusts = emptyAdjust()
    fun check(player: Int, target: Int): Boolean {
        return player > target
    }

    fun abilityCheck(type: String, target: Int): Boolean{
        val value = abilityCheckAdjusts.fold(0, {acc, x -> acc + x.first.getByName(type)})
        val times = abilityCheckTimesAdjusts.fold(0, {acc, x -> acc + x.first})
        var result= 0
        for (i in 0..times){
            result += d20()
        }
        result += value * times
        return check(result, target)
    }

}

fun randomPointAttribute(range: () -> Int = { randomTo(15) }): BaseCharacterAttribute {
    val data = BaseCharacterAttribute()
    data.applyAll { range() }
    return data
}

fun randomFixedTotalPointAttribute(points: Int = 80): BaseCharacterAttribute {
    return randomFixedTotalPointAttribute(points, { randomTo(15) })
}

fun randomFixedTotalPointAttribute(points: Int = 80, randomFunc: () -> Int,
                                   base: BaseCharacterAttribute = ZeroAttr): BaseCharacterAttribute {
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

fun randomAttribute(points: Int = 32): BaseCharacterAttribute {
    val data = BaseCharacterAttribute()
    data.lowerAll(2)
    val adjust = randomFixedTotalPointAttribute(points, { randomTo(7) }, data)
    return adjust
}

val ZeroAttr: BaseCharacterAttribute = BaseCharacterAttribute().lowerAll(10)
