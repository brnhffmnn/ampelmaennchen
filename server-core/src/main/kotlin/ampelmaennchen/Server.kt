package ampelmaennchen

import ampelmaennchen.lights.PedestrianLightControl
import ampelmaennchen.routes.index
import ampelmaennchen.routes.jobStates
import ampelmaennchen.routes.manual
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.content.TextContent
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.features.StatusPages
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.logging.CallLogging
import org.jetbrains.ktor.routing.Routing

class Server(app: Application, pedestrianLightControl: PedestrianLightControl) {

    init {
        app.install(DefaultHeaders)
        app.install(CallLogging)
        app.install(StatusPages) {
            exception<NotImplementedError> { call.respond(HttpStatusCode.NotImplemented) }
            exception<IllegalArgumentException> { call.respond(TextContent(text = it.message.orEmpty(), status = HttpStatusCode.BadRequest)) }
        }

        app.install(Routing) {
            index(
                    manual(pedestrianLightControl),
                    jobStates(pedestrianLightControl)
            )
        }
    }
}