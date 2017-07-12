package com.aoe.ampelmaennchen

import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.application.install
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.features.StatusPages
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.logging.CallLogging
import org.jetbrains.ktor.routing.Routing

class Server(app: Application) {

    init {
        app.install(DefaultHeaders)
        app.install(CallLogging)
        app.install(StatusPages) {
            exception<NotImplementedError> { call.respond(HttpStatusCode.NotImplemented) }
        }

        app.install(Routing) {
            index()
            manual()
        }
    }
}