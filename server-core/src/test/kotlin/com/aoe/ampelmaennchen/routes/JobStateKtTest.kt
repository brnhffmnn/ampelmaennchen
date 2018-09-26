package com.aoe.ampelmaennchen.routes

import com.aoe.ampelmaennchen.test
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.http.HttpMethod
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.testing.handleRequest
import org.jetbrains.ktor.testing.withTestApplication

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