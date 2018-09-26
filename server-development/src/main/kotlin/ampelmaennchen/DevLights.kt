package ampelmaennchen

class DevGreenLight(override val name: String = "Green") : DevLightSwitch()

class DevRedLight(override val name: String = "Red") : DevLightSwitch()