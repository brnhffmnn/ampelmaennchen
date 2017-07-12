package com.aoe.ampelmaennchen

import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.routing.accept
import org.jetbrains.ktor.routing.get

fun Route.index() {
    accept(ContentType.Text.Any) {
        get {
            call.respond("""
                        | /                                 -> this page
                        | /manual                           -> manually trigger lights
                        |      - /green/{on|off}            -> turn green light on or off
                        |      - /red/{on|off}              -> turn red light on or off
                        |
                         """.trimMargin())
        }
    }
}