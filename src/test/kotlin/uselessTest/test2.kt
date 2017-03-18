import junit.framework.TestCase
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.subject.SubjectSpek
import org.junit.platform.runner.JUnitPlatform
import org.junit.runner.RunWith
import test.Calculator

@RunWith(JUnitPlatform::class)
object SimpleCalculatorSpec: SubjectSpek<Calculator>({
    subject { Calculator() }

    it("should return the result of adding the first number to the second number") {
        TestCase.assertEquals(6, subject.sum(2, 4))
    }

    it("should return the result of subtracting the second number from the first number") {
        TestCase.assertEquals(2, subject.subtract(4, 2))
    }
})
