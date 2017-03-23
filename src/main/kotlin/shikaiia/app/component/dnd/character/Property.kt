package shikaiia.app.component.dnd.character

import shikaiia.app.component.dnd.core.d20

open class Property(var value: Int = 0) {
    var adjusts: HashMap<String, Pair<Int, String>> = emptyHashMap()
    var timesAdjusts: HashMap<String, Pair<Int, String>> = emptyHashMap()

    val sum get() = this.adjusts.values.sumBy { it.first }.modifier()
    val times get() = this.timesAdjusts.values.sumBy { it.first }
    fun calculate() = (0..times).toList().reduce { acc, i -> acc + d20() } + sum * times


}

val ZeroProperty = Property()