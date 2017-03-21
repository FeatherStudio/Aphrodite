package shikaiia.app.component.dnd.character

// Weapon

open class BaseEquipment
open class BaseWeapen : BaseEquipment()
open class BaseArmor : BaseEquipment()

typealias Equipments = Array<BaseEquipment?>

class EquipmentSet {
    private var equipments: Equipments =
            arrayOf(null, null, null, null, null, null,
                    null, null, null, null, null, null)
    var head get() = equipments[0]; set(value) {
        equipments[0] = value
    }
    var body get() = equipments[1]; set(value) {
        equipments[1] = value
    }
    var leg get() = equipments[2]; set(value) {
        equipments[2] = value
    }
    var foot get() = equipments[3]; set(value) {
        equipments[3] = value
    }
    var cloak get() = equipments[4]; set(value) {
        equipments[4] = value
    }
    var belt get() = equipments[5]; set(value) {
        equipments[5] = value
    }
    var leftHand get() = equipments[6]; set(value) {
        equipments[6] = value
    }
    var rightHand get() = equipments[7]; set(value) {
        equipments[7] = value
    }
    var bracelet get() = equipments[8]; set(value) {
        equipments[8] = value
    }
    var necklace get() = equipments[9]; set(value) {
        equipments[9] = value
    }
    var ring1 get() = equipments[10]; set(value) {
        equipments[10] = value
    }
    var ring2 get() = equipments[11]; set(value) {
        equipments[11] = value
    }

}

class ProficiencySet {

    private var attrList: IntArray = intArrayOf(0, 0, 0, 0, 0, 0, 0, 0, 0)
    // todo adjust list
    var oneHand get() = attrList[0]; set(value) {
        attrList[0] = value
    }
    var twoHand get() = attrList[1]; set(value) {
        attrList[1] = value
    }
    var bow get() = attrList[2]; set(value) {
        attrList[2] = value
    }
    var staff get() = attrList[3]; set(value) {
        attrList[3] = value
    }
    var lightArmor get() = attrList[4]; set(value) {
        attrList[4] = value
    }
    var mediumArmor get() = attrList[5]; set(value) {
        attrList[5] = value
    }
    var heavyArmor get() = attrList[6]; set(value) {
        attrList[5] = value
    }
    var shield get() = attrList[7]; set(value) {
        attrList[5] = value
    }
    var noArmor get() = attrList[8]; set(value) {
        attrList[5] = value
    }

}

