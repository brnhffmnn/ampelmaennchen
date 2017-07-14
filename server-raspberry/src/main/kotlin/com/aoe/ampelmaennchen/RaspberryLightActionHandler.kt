package com.aoe.ampelmaennchen

import com.aoe.ampelmaennchen.lights.LightActionCallHandler
import com.aoe.ampelmaennchen.lights.actions.Action
import com.aoe.ampelmaennchen.lights.actions.UnsupportedNoOpAction
import org.jetbrains.ktor.application.ApplicationCall
import org.jetbrains.ktor.http.HttpStatusCode


class RaspberryLightActionHandler : LightActionCallHandler {

    suspend override fun perform(call: ApplicationCall, action: Action) {
        action.perform()

        when (action) {
            !is UnsupportedNoOpAction -> call.response.status(HttpStatusCode.NotFound)
            else -> call.response.status(HttpStatusCode.OK)
        }
    }
}