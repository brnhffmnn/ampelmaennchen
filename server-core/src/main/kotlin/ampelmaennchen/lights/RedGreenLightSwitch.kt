package ampelmaennchen.lights

interface RedGreenLightSwitch : LightSwitch, RedGreenLight {

    override val redLight: LightSwitch
    override val greenLight: LightSwitch
}