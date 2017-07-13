package com.aoe.ampelmaennchen.lights.actions

import com.aoe.ampelmaennchen.lights.Light
import com.aoe.ampelmaennchen.lights.LightSwitch

/**
 * Switch on a light
 */
class SwitchOnLight(private val lightSwitch: LightSwitch) : Action {

    override val light: Light = lightSwitch

    override fun perform() {
        lightSwitch.switchOn()
    }
}