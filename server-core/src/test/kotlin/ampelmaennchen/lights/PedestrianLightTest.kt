package ampelmaennchen.lights

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.ShouldSpec

class PedestrianLightTest : ShouldSpec({
    "save+restore state" {

        val testlight = createTestPedestrianLight()

        should("restore saved state") {
            testlight.greenLight.switchOn()

            val sp = testlight.saveState()
            testlight.redLight.switchOn()
            testlight.greenLight.switchOff()

            testlight.redLight.isOn shouldBe true
            testlight.greenLight.isOn shouldBe false

            testlight.restoreState(sp)

            testlight.redLight.isOn shouldBe false
            testlight.greenLight.isOn shouldBe true
        }

        should("restore multiple saved state") {
            testlight.greenLight.switchOn()

            val sp = testlight.saveState()
            testlight.redLight.switchOn()
            testlight.greenLight.switchOff()

            testlight.redLight.isOn shouldBe true
            testlight.greenLight.isOn shouldBe false

            testlight.saveState()
            testlight.redLight.switchOff()
            testlight.greenLight.switchOff()

            testlight.redLight.isOn shouldBe false
            testlight.greenLight.isOn shouldBe false

            testlight.restoreState(sp)

            testlight.redLight.isOn shouldBe false
            testlight.greenLight.isOn shouldBe true
        }
    }
})