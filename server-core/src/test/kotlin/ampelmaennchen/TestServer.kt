package ampelmaennchen

import ampelmaennchen.lights.LightActionCallHandler
import ampelmaennchen.lights.PedestrianLight
import ampelmaennchen.lights.PedestrianLightControl
import ampelmaennchen.lights.createTestPedestrianLight
import io.ktor.application.Application

fun createTestServer(app: Application,
                     pedestrianLight: PedestrianLight = createTestPedestrianLight(),
                     actionHandler: LightActionCallHandler) {

    val lightControl = PedestrianLightControl(pedestrianLight, actionHandler)

    Server(app, lightControl)
}