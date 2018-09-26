package com.aoe.ampelmaennchen

import com.aoe.ampelmaennchen.lights.LightActionCallHandler
import com.aoe.ampelmaennchen.lights.actions.JobStateAction
import com.aoe.ampelmaennchen.lights.actions.LightActionCallable
import com.aoe.ampelmaennchen.lights.actions.LightActionRunnable
import com.aoe.ampelmaennchen.lights.actions.UnsupportedNoOpAction
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.ApplicationCall
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.response.respondText

fun Application.test() {
    createTestServer(this, actionHandler = object : LightActionCallHandler {
        suspend override fun perform(call: ApplicationCall, action: LightActionRunnable) {
            action.run()
            when (action) {
                is UnsupportedNoOpAction -> with(call) {
                    response.status(HttpStatusCode.NotFound)
                    respondText("test")
                }
            }
        }

        suspend override fun perform(call: ApplicationCall, action: LightActionCallable<out Any>) {
            with(action as JobStateAction) {
                with(call()) {
                    call.respondText("test")
                }
            }
        }
    })
}