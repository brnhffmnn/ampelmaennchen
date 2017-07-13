package com.aoe.ampelmaennchen

import com.aoe.ampelmaennchen.lights.LightActionCallHandler
import com.aoe.ampelmaennchen.lights.actions.Action
import org.jetbrains.ktor.application.ApplicationCall
import org.jetbrains.ktor.http.HttpStatusCode


class RaspberryLightActionHandler : LightActionCallHandler {

    suspend override fun perform(call: ApplicationCall, action: Action) {
        call.response.status(HttpStatusCode.InternalServerError)
    }
}