package app.component.dnd

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import shikaiia.app.component.dnd.character.BaseCharacterAttribute
import shikaiia.app.component.dnd.character.randomCharacter
import shikaiia.app.component.dnd.character.randomFixedTotalPointCharacter
import shikaiia.extension.public.repeat


@RunWith(JUnitPlatform::class)
object CharacterSpec : Spek({
    describe("a group") {
        it("should random generate some attributes set") {
            repeat(10, {
                val data = randomFixedTotalPointCharacter(60)
                assertEquals(data.total(), 60)
//                data.print()
//                println("===")
            })
            repeat(2, {
                val data = randomCharacter()
                assertEquals(data.total(), 78)
                data.print()
                println("===")
            })
        }

        it("should be 9 after lower all by 1"){
            val data = BaseCharacterAttribute()
            data.lowerAll(1)
            data.getAttrList().forEach { assertEquals(it, 9);  assertTrue("attr should greater than 5", it >= 5)}
        }

    }
})
