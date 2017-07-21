package com.aoe.ampelmaennchen.lights.actions

import com.aoe.ampelmaennchen.lights.Light
import com.aoe.ampelmaennchen.lights.LightSwitch

/**
 * Switch off a light
 */
class SwitchOffLight(private val lightSwitch: LightSwitch) : LightActionRunnable {

    override val light: Light = lightSwitch

    override fun run() {
        lightSwitch.switchOff()
    }
}