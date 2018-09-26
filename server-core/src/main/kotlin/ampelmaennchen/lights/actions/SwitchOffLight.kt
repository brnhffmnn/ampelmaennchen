package ampelmaennchen.lights.actions

import ampelmaennchen.lights.Light
import ampelmaennchen.lights.LightSwitch

/**
 * Switch off a light
 */
class SwitchOffLight(private val lightSwitch: LightSwitch) : LightActionRunnable {

    override val light: Light = lightSwitch

    override fun run() {
        lightSwitch.switchOff()
    }
}