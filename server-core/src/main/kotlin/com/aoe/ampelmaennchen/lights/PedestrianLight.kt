package com.aoe.ampelmaennchen.lights

data class PedestrianLight(val redLight: LightSwitch,
                           val greenLight: LightSwitch) : Light {

    override val name: String = "Pedestrian"

    override val isOn: Boolean
        get() = redLight.isOn or greenLight.isOn
}