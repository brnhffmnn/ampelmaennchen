package ampelmaennchen

import ampelmaennchen.lights.LightSwitch
import com.pi4j.io.gpio.GpioPinDigitalOutput

class RaspberryLightSwitch(
        override val name: String,
        private val gpioPinDigitalOutput: GpioPinDigitalOutput) : LightSwitch {

    override val isOn: Boolean
        get() = !gpioPinDigitalOutput.isHigh

    override fun switchOn(): Boolean {
        if (!isOn) {
            gpioPinDigitalOutput.low()
        }

        return isOn
    }

    override fun switchOff(): Boolean {
        if (isOn) {
            gpioPinDigitalOutput.high()
        }

        return !isOn
    }

}