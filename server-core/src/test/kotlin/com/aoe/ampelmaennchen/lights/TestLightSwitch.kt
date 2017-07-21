package com.aoe.ampelmaennchen.lights

class TestLightSwitch(override val name: String, override var isOn: Boolean) : LightSwitch {

    override fun switchOn(): Boolean {
        isOn = true
        return true
    }

    override fun switchOff(): Boolean {
        isOn = false
        return true
    }
}