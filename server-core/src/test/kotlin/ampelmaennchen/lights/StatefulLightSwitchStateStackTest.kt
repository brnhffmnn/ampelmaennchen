package ampelmaennchen.lights

import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.ShouldSpec

class StatefulLightSwitchStateStackTest : ShouldSpec({

    fun testStack(block: StatefulLightSwitch.StateStack<String>.() -> Unit) = StatefulLightSwitch.StateStack<String>().apply { block() }

    should("push state") {
        testStack {
            push("a") shouldBe 0
            isEmpty() shouldBe false
        }
    }

    should("pop single state") {
        testStack {
            push("a") shouldBe 0
            popUntil(0) shouldBe "a"
        }
    }

    should("pop multiple states") {
        testStack {
            push("a") shouldBe 0
            push("b") shouldBe 1
            push("c") shouldBe 2

            popUntil(1) shouldBe "b"
            isEmpty() shouldBe false
        }
    }

    should("throw on pop when empty") {
        testStack {
            isEmpty() shouldBe true

            shouldThrow<IllegalStateException> {
                popUntil(0)
            }
        }
    }

    should("throw on pop out of bounds") {
        testStack {
            push("a") shouldBe 0

            shouldThrow<IllegalArgumentException> {
                popUntil(1)
            }
        }
    }
})