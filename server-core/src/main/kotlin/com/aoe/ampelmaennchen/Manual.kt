package com.aoe.ampelmaennchen

import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.routing.get
import org.jetbrains.ktor.routing.route

fun Route.manual() {
    route("manual") {
        get {
            call.respond("")
        }
        manualOnOff("red")
        manualOnOff("green")
    }
}

private fun Route.manualOnOff(light: String) {
    get("$light/{action?}") {
        when (call.parameters["action"]) {
            "on" -> call.respondText("$light light turning on")
            "off" -> call.respondText("$light light turning off")
            else -> call.respondText("Not sure what to do with $light light ... o.O")
        }
    }
}