package ampelmaennchen.lights

class PedestrianLight(override val redLight: LightSwitch,
                      override val greenLight: LightSwitch) : RedGreenLightSwitch, StatefulLightSwitch {

    override val name: String = "Pedestrian"

    private val stateStack = StatefulLightSwitch.StateStack<Pair<Boolean, Boolean>>()

    override val isOn: Boolean
        get() = redLight.isOn or greenLight.isOn

    override fun switchOn(): Boolean {
        return redLight.switchOn() and greenLight.switchOn()
    }

    override fun switchOff(): Boolean {
        return redLight.switchOff() and greenLight.switchOff()
    }

    override fun saveState(): Int = stateStack.push(redLight.isOn to greenLight.isOn)

    override fun restoreState(savepoint: Int) {
        val (redOn, greenOn) = stateStack.popUntil(savepoint)

        if (redOn) redLight.switchOn() else redLight.switchOff()
        if (greenOn) greenLight.switchOn() else greenLight.switchOff()
    }
}
