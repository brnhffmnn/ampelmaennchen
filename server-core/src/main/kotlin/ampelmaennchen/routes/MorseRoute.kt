package ampelmaennchen.routes

import ampelmaennchen.lights.PedestrianLightControl
import ampelmaennchen.lights.actions.MorseAction
import io.ktor.application.call
import io.ktor.http.decodeURLPart
import io.ktor.routing.Route
import io.ktor.routing.get

fun Route.morse(pedestrianLightControl: PedestrianLightControl): RouteDescriptor =
        describedParentRoute("Morse a message") {
            get("morse/{message}") {
                val messageRaw = requireNotNull(call.parameters["message"]) { "I need a message!" }
                val message = messageRaw.decodeURLPart()

                pedestrianLightControl.actionHandler.perform(call, MorseAction(pedestrianLightControl.pedestrianLight, message))
            }
        }