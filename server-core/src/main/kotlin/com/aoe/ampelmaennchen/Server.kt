package com.aoe.ampelmaennchen

import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.logging.CallLogging
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.Routing
import org.jetbrains.ktor.routing.get

class Server(app: Application) {

    init {
        app.install(DefaultHeaders)
        app.install(CallLogging)
        app.install(Routing) {
            get("/") {
                call.respondText("Hello", ContentType.Text.Plain)
            }
        }
    }
}