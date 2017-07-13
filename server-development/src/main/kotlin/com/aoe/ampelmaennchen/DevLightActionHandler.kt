package com.aoe.ampelmaennchen

import com.aoe.ampelmaennchen.lights.LightActionCallHandler
import com.aoe.ampelmaennchen.lights.actions.Action
import com.aoe.ampelmaennchen.lights.actions.SwitchOffLight
import com.aoe.ampelmaennchen.lights.actions.SwitchOnLight
import com.aoe.ampelmaennchen.lights.actions.UnsupportedNoOpAction
import org.jetbrains.ktor.application.ApplicationCall
import org.jetbrains.ktor.response.respondText

class DevLightActionHandler : LightActionCallHandler {

    suspend override fun perform(call: ApplicationCall, action: Action) {
        action.perform()

        when (action) {
            is SwitchOffLight -> call.respondText("${action.light.name} light turning off")
            is SwitchOnLight -> call.respondText("${action.light.name} light turning on")
            is UnsupportedNoOpAction -> call.respondText("Not sure what to do with ${action.light.name} light ... o.O")
        }
    }
}