package com.aoe.ampelmaennchen.routes

import com.aoe.ampelmaennchen.createTestServer
import com.aoe.ampelmaennchen.lights.LightActionCallHandler
import com.aoe.ampelmaennchen.lights.actions.JobStateAction
import com.aoe.ampelmaennchen.lights.actions.LightActionCallable
import com.aoe.ampelmaennchen.lights.actions.LightActionRunnable
import com.aoe.ampelmaennchen.lights.actions.UnsupportedNoOpAction
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.application.ApplicationCall
import org.jetbrains.ktor.http.HttpMethod
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.testing.handleRequest
import org.jetbrains.ktor.testing.withTestApplication

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

class JobStateKtTest : FeatureSpec({

    feature("/job-states") {
        scenario("handle request with valid json") {
            withTestApplication(Application::test) {
                with(handleRequest(HttpMethod.Post, "/job-states") {
                    body = """ ["SUCCESS", "SUCCESS"] """
                }) {
                    requestHandled shouldBe true
                    responded shouldBe true
                    response.status() shouldBe HttpStatusCode.OK
                }
            }
        }

        scenario("handle request") {
            withTestApplication(Application::test) {
                with(handleRequest(HttpMethod.Post, "/job-states") {
                    body = ""
                }) {
                    requestHandled shouldBe true
                    responded shouldBe true
                    response.status() shouldBe HttpStatusCode.NotFound
                }
            }
        }
    }
})