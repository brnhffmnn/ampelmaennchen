package ampelmaennchen.routes

import ampelmaennchen.test
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.http.HttpHeaders
import org.jetbrains.ktor.http.HttpMethod
import org.jetbrains.ktor.http.HttpStatusCode
import org.jetbrains.ktor.testing.handleRequest
import org.jetbrains.ktor.testing.withTestApplication

class JobStateKtTest : FeatureSpec({

    feature("/job-states") {
        scenario("handle request with valid json") {
            withTestApplication(Application::test) {
                handleRequest(HttpMethod.Post, "/job-states") {
                    addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                    body = """["SUCCESS", "SUCCESS"]"""
                }.apply {
                    requestHandled shouldBe true
                    responded shouldBe true
                    response.status() shouldBe HttpStatusCode.OK
                }
            }
        }

        scenario("handle request") {
            withTestApplication(Application::test) {
                handleRequest(HttpMethod.Post, "/job-states") {
                    addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                    body = ""
                }.apply {
                    requestHandled shouldBe true
                    responded shouldBe true
                    response.status() shouldBe HttpStatusCode.OK
                }
            }
        }
    }
})