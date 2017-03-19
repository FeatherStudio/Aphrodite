package uselessTest

import junit.framework.TestCase.assertEquals
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.context
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
object FixtureSpec : Spek({
    describe("a group") {
        beforeGroup {
            println("before group")
        }

        beforeEachTest {
            println("before each")
        }

        context("a nested group") {

            beforeEachTest {
                println("before each nested")
            }

            beforeEachTest {
                println("before each nested 2")
            }

            it("should work") { assertEquals(6, 6); println("test 1 nested") }
        }

        it("do something") { assertEquals(6, 6); println("test 2 outer") }

        afterEachTest {
            println("after each test")
        }

        afterGroup {
            println("after group")
        }
    }
})
