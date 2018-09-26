package ampelmaennchen

import ampelmaennchen.lights.LightActionCallHandler
import ampelmaennchen.lights.actions.LightAction
import ampelmaennchen.lights.actions.LightActionCallable
import ampelmaennchen.lights.actions.LightActionRunnable
import ampelmaennchen.lights.actions.UnsupportedNoOpAction
import org.jetbrains.ktor.application.ApplicationCall
import org.jetbrains.ktor.http.HttpStatusCode


class RaspberryLightActionHandler : LightActionCallHandler {

    suspend override fun perform(call: ApplicationCall, action: LightActionCallable<out Any>) {
        action.call()
        handleAction(call, action)
    }

    suspend override fun perform(call: ApplicationCall, action: LightActionRunnable) {
        action.run()
        handleAction(call, action)
    }

    private fun handleAction(call: ApplicationCall, action: LightAction) {
        when (action) {
            is UnsupportedNoOpAction -> call.response.status(HttpStatusCode.NotFound)
            else -> call.response.status(HttpStatusCode.OK)
        }
    }
}