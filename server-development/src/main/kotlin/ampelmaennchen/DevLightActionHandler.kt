package ampelmaennchen

import ampelmaennchen.lights.LightActionCallHandler
import ampelmaennchen.lights.actions.JobStateAction
import ampelmaennchen.lights.actions.LightAction
import ampelmaennchen.lights.actions.LightActionCallable
import ampelmaennchen.lights.actions.LightActionRunnable
import io.ktor.application.ApplicationCall
import io.ktor.response.respondText

class DevLightActionHandler : LightActionCallHandler {

    override suspend fun perform(call: ApplicationCall, action: LightActionCallable<out Any>) {
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

    override suspend fun perform(call: ApplicationCall, action: LightActionRunnable) {
        action.run()
        handleAction(call, action)
    }

    private suspend fun handleAction(call: ApplicationCall, action: LightAction) {
        call.respondText("${action.light.name} is now ${if (action.light.isOn) "on" else "off"}")
    }
}