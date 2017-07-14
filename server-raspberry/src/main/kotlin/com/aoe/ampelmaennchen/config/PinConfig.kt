package com.aoe.ampelmaennchen.config

import com.pi4j.io.gpio.Pin
import com.pi4j.io.gpio.PinState
import com.pi4j.io.gpio.RaspiPin

/**
 * Information about a pin configuration
 */
data class PinConfig(val name: String, val pinNumber: Int, val startValue: Int) {

    val pin: Pin?
        get() = RaspiPin.getPinByAddress(pinNumber)

    val pinState: PinState?
        get() = PinState.getState(startValue)
}