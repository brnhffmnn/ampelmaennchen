package com.aoe.ampelmaennchen

import com.aoe.ampelmaennchen.lights.LightSwitch
import com.pi4j.io.gpio.GpioPinDigitalOutput

class RaspberryLightSwitch(
        override val name: String,
        private val gpioPinDigitalOutput: GpioPinDigitalOutput) : LightSwitch {

    override val isOn: Boolean
        get() = !gpioPinDigitalOutput.isHigh

    override fun switchOn(): Boolean {
        gpioPinDigitalOutput.low()
        return isOn
    }

    override fun switchOff(): Boolean {
        gpioPinDigitalOutput.high()
        return !isOn
    }

}