package shikaiia.app.component.dnd.character

// Weapon

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

