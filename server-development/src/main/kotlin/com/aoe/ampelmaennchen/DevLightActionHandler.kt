package com.aoe.ampelmaennchen

import com.aoe.ampelmaennchen.lights.LightActionCallHandler
import com.aoe.ampelmaennchen.lights.actions.*
import org.jetbrains.ktor.application.ApplicationCall
import org.jetbrains.ktor.response.respondText

class DevLightActionHandler : LightActionCallHandler {

    suspend override fun perform(call: ApplicationCall, action: LightActionCallable<out Any>) {
        when (action) {
            is JobStateAction -> {
                val result = action.call()
                call.respondText(
                        """
                        | Red light is on? -> ${result.first}.
                        | Green light is on? -> ${result.second}.
                        """.trimMargin())
            }

            else -> handleAction(call, action)
        }
    }

    suspend override fun perform(call: ApplicationCall, action: LightActionRunnable) {
        action.run()
        handleAction(call, action)
    }

    private suspend fun handleAction(call: ApplicationCall, action: LightAction) {
        when (action) {
            is SwitchOffLight -> call.respondText("${action.light.name} light turning off")
            is SwitchOnLight -> call.respondText("${action.light.name} light turning on")
            is UnsupportedNoOpAction -> call.respondText("Not sure what to do with ${action.light.name} light ... o.O")
        }
    }
}