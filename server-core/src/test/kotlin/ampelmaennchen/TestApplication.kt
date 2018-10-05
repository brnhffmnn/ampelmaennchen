package ampelmaennchen

import ampelmaennchen.lights.LightActionCallHandler
import ampelmaennchen.lights.actions.LightActionCallable
import ampelmaennchen.lights.actions.LightActionRunnable
import io.ktor.application.Application
import io.ktor.application.ApplicationCall
import io.ktor.response.respondText

fun Application.test() {
    createTestServer(this, actionHandler = object : LightActionCallHandler {
        override suspend fun perform(call: ApplicationCall, action: LightActionRunnable) {
            action.run()
            call.respondText("tested: $action")
        }

        override suspend fun perform(call: ApplicationCall, action: LightActionCallable<out Any>) {
            val result = action.call()
            call.respondText("tested: $action => $result")
        }
    })
}