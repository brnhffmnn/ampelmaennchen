package ampelmaennchen.lights

/**
 * A [Light] that can be switched on and off
 */
interface LightSwitch : Light {

    fun switchOn(): Boolean
    fun switchOff(): Boolean

    fun toggle(): Boolean = when (isOn) {
        true -> switchOff()
        false -> switchOn()
    }
}