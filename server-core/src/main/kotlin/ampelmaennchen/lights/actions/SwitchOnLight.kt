package ampelmaennchen.lights.actions

import ampelmaennchen.lights.Light
import ampelmaennchen.lights.LightSwitch

/**
 * Switch on a light
 */
class SwitchOnLight(private val lightSwitch: LightSwitch) : LightActionRunnable {

    override val light: Light = lightSwitch

    override fun run() {
        lightSwitch.switchOn()
    }
}