package com.aoe.ampelmaennchen.lights

class PedestrianLight(override val redLight: LightSwitch,
                      override val greenLight: LightSwitch) : RedGreenLightSwitch {
    override val name: String = "Pedestrian"

    override val isOn: Boolean
        get() = redLight.isOn or greenLight.isOn

    override fun switchOn(): Boolean {
        return redLight.switchOn() and greenLight.switchOn()
    }

    override fun switchOff(): Boolean {
        return redLight.switchOff() and greenLight.switchOff()
    }
}