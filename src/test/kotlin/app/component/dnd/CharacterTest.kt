package app.component.dnd

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import shikaiia.app.component.dnd.character.BaseCharacterAttribute
import shikaiia.app.component.dnd.character.randomAttribute
import shikaiia.extension.public.repeat


@RunWith(JUnitPlatform::class)
object CharacterSpec : Spek({
    describe("a group") {
        it("should random generate some attributes set") {
            repeat(2, {
                val data = randomAttribute()
//                data.print()
                assertEquals(80, data.totalConsume())
//                println("===")
            })
        }

        it("should be 9 after lower all by 1") {
            val data = BaseCharacterAttribute()
            data.lowerAll(1)
            data.getAttrList().forEach { assertEquals(it, 9); assertTrue("attr should greater than 5", it >= 5) }
        }

        it("should consume 80 points") {
            val dataA = BaseCharacterAttribute(intArrayOf(13, 16, 11, 12, 13, 13))
            val dataB = BaseCharacterAttribute(intArrayOf(15, 16, 10, 10, 13, 13))
            assertEquals(80, dataA.totalConsume())
            assertEquals(80, dataB.totalConsume())
        }
    }
})
