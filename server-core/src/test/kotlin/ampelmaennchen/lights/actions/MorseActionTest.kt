package ampelmaennchen.lights.actions

import ampelmaennchen.TestCoroutineDispatcher
import ampelmaennchen.lights.StatefulLightSwitch
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.ShouldSpec
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory

class MorseActionTest : ShouldSpec({

    class TestLight : StatefulLightSwitch {

        val log = LoggerFactory.getLogger(TestLight::class.java)

        var on = false
        override val isOn: Boolean get() = on
        override val name = "TestLight"

        val stateStack = StatefulLightSwitch.StateStack<Boolean>()
        val switchActions = mutableListOf<Boolean>()

        override fun switchOn(): Boolean {
            log.info("switch on")

            switchActions += true
            on = true
            return on
        }

        override fun switchOff(): Boolean {
            log.info("switch off")

            switchActions += false
            on = false
            return on
        }

        override fun saveState(): Int = stateStack.push(on)

        override fun restoreState(savepoint: Int) {
            if (stateStack.popUntil(savepoint)) switchOn() else switchOff()
        }
    }

    fun testMorse(msg: String, block: TestLight.() -> Unit) {
        runBlocking(TestCoroutineDispatcher()) {
            val light = TestLight()
            MorseAction(light, msg).run()
            light.apply { block() }
        }
    }

    should("morse message") {
        testMorse("E") {
            switchActions.size % 2 shouldBe 0

            // 1. turn off before morsing
            switchActions[0] shouldBe false
            // 2. turn on for E
            switchActions[1] shouldBe true
            // 3. turn off the E
            switchActions[2] shouldBe false
            // 4. restore initial state off
            switchActions[3] shouldBe false

            stateStack.isEmpty() shouldBe true
        }
    }

    should("reject unmorsable message") {
        shouldThrow<IllegalArgumentException> {
            testMorse("$") { }
        }
    }

})