package ampelmaennchen

import ampelmaennchen.lights.LightSwitch
import org.slf4j.LoggerFactory

abstract class DevLightSwitch : LightSwitch {

    companion object {
        private val log = LoggerFactory.getLogger(DevLightSwitch::class.java)
    }

    private var stateIsOn: Boolean = false

    override val isOn: Boolean
        get() = stateIsOn

    override fun switchOn(): Boolean {
        log.info("{} switch on", name)

        stateIsOn = true
        return true
    }

    override fun switchOff(): Boolean {
        log.info("{} switch off", name)

        stateIsOn = false
        return true
    }
}