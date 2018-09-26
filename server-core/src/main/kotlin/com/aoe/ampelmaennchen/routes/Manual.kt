package com.aoe.ampelmaennchen.routes

import com.aoe.ampelmaennchen.lights.LightSwitch
import com.aoe.ampelmaennchen.lights.PedestrianLightControl
import com.aoe.ampelmaennchen.lights.actions.GoCrazyAction
import com.aoe.ampelmaennchen.lights.actions.SwitchOffLight
import com.aoe.ampelmaennchen.lights.actions.SwitchOnLight
import com.aoe.ampelmaennchen.lights.actions.UnsupportedNoOpAction
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.routing.get
import org.jetbrains.ktor.routing.route
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