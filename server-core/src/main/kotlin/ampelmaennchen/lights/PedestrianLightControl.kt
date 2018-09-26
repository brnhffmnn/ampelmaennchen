package ampelmaennchen.lights

/**
 * A control unit that encapsulates knowledge of [PedestrianLight] and a [LightActionCallHandler]
 */
class PedestrianLightControl(val pedestrianLight: PedestrianLight,
                             val actionHandler: LightActionCallHandler)