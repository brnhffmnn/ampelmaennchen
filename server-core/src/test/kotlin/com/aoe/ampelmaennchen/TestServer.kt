package com.aoe.ampelmaennchen

import com.aoe.ampelmaennchen.lights.*
import org.jetbrains.ktor.application.Application

fun createTestPedestrianLight(redLight: LightSwitch = TestLightSwitch("red", false),
                              greenLight: LightSwitch = TestLightSwitch("green", false))
        = PedestrianLight(redLight, greenLight)

fun createTestServer(app: Application,
                     pedestrianLight: PedestrianLight = createTestPedestrianLight(),
                     actionHandler: LightActionCallHandler) {

    val lightControl = PedestrianLightControl(pedestrianLight, actionHandler)

    Server(app, lightControl)
}