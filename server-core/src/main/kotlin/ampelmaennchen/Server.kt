package ampelmaennchen

import ampelmaennchen.lights.PedestrianLightControl
import ampelmaennchen.routes.index
import ampelmaennchen.routes.jobStates
import ampelmaennchen.routes.manual
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing

class Server(app: Application, pedestrianLightControl: PedestrianLightControl) {

    init {
        app.install(DefaultHeaders)
        app.install(CallLogging)
        app.install(StatusPages) {
            exception<NotImplementedError> { call.respond(HttpStatusCode.NotImplemented) }
            exception<IllegalArgumentException> {
                call.respondText(status = HttpStatusCode.BadRequest) { it.message.orEmpty() }
            }
        }

        app.install(ContentNegotiation) {
            jackson { }
        }

        app.install(Routing) {
            index(
                    manual(pedestrianLightControl),
                    jobStates(pedestrianLightControl)
            )
        }
    }
}