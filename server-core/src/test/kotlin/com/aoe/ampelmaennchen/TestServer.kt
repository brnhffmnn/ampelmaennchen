package com.aoe.ampelmaennchen

import com.aoe.ampelmaennchen.lights.*
import org.jetbrains.ktor.application.Application

fun createTestServer(app: Application,
                     pedestrianLight: PedestrianLight = createTestPedestrianLight(),
                     actionHandler: LightActionCallHandler) {

    val lightControl = PedestrianLightControl(pedestrianLight, actionHandler)

    Server(app, lightControl)
}