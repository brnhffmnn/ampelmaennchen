package com.aoe.ampelmaennchen

import com.aoe.ampelmaennchen.lights.LightSwitch

abstract class DevLightSwitch : LightSwitch {

    private var stateIsOn: Boolean = false

    override val isOn: Boolean
        get() = stateIsOn

    override fun switchOn(): Boolean {
        stateIsOn = true
        return true
    }

    override fun switchOff(): Boolean {
        stateIsOn = false
        return true
    }
}