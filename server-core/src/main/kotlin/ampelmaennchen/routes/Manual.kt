package ampelmaennchen.routes

import ampelmaennchen.lights.LightSwitch
import ampelmaennchen.lights.PedestrianLightControl
import ampelmaennchen.lights.actions.GoCrazyAction
import ampelmaennchen.lights.actions.SwitchOffLight
import ampelmaennchen.lights.actions.SwitchOnLight
import ampelmaennchen.lights.actions.UnsupportedNoOpAction
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.get
import io.ktor.routing.route
import java.time.Duration

fun Route.manual(pedestrianLightControl: PedestrianLightControl): RouteDescriptor =
        describedParentRoute("manually trigger lights") {
            route("manually") {
                get {
                    call.respond("")
                }
            }
        }.withDescripedChild("turn red light on or off") {
            manualOnOff(pedestrianLightControl, pedestrianLightControl.pedestrianLight.redLight)
        }.withDescripedChild("turn green light on or off") {
            manualOnOff(pedestrianLightControl, pedestrianLightControl.pedestrianLight.greenLight)
        }.withDescripedChild("go crazy") {
            get("go-crazy/{duration?}") {
                val duration = call.parameters["duration"]
                        ?.toLongOrNull()
                        ?.let { Duration.ofSeconds(it) }

                pedestrianLightControl.actionHandler.perform(call, GoCrazyAction(pedestrianLightControl.pedestrianLight, duration))
            }
        }


private fun Route.manualOnOff(pedestrianLightControl: PedestrianLightControl, light: LightSwitch): Route =
        get("${light.name.toLowerCase()}/{action?}") {
            when (call.parameters["action"]) {
                "on" -> pedestrianLightControl.actionHandler.perform(call, SwitchOnLight(light))
                "off" -> pedestrianLightControl.actionHandler.perform(call, SwitchOffLight(light))
                else -> pedestrianLightControl.actionHandler.perform(call, UnsupportedNoOpAction(light))
            }
        }