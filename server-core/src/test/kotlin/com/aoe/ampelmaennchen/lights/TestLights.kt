package com.aoe.ampelmaennchen.lights

class TestLightSwitches(override val name: String, override var isOn: Boolean) : LightSwitch {

    override fun switchOn(): Boolean {
        isOn = true
        return true
    }

    override fun switchOff(): Boolean {
        isOn = false
        return true
    }
}

class TestRedGreenLightSwitch(
        override val redLight: LightSwitch,
        override val greenLight: LightSwitch,
        override val name: String,
        override var isOn: Boolean
) : RedGreenLightSwitch {

    override fun switchOn(): Boolean {
        isOn = true
        return true
    }

    override fun switchOff(): Boolean {
        isOn = false
        return true
    }
}

fun testRedGreenLightSwitch(): RedGreenLightSwitch = TestRedGreenLightSwitch(
        TestLightSwitches("red", false),
        TestLightSwitches("green", false),
        "redgreen",
        false
)

fun createTestPedestrianLight(redLight: LightSwitch = TestLightSwitches("red", false),
                              greenLight: LightSwitch = TestLightSwitches("green", false)) = PedestrianLight(redLight, greenLight)