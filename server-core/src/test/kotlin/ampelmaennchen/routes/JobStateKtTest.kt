package ampelmaennchen.routes

import ampelmaennchen.test
import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.FeatureSpec
import io.ktor.application.Application
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.setBody
import io.ktor.server.testing.withTestApplication

class JobStateKtTest : FeatureSpec({

    feature("/job-states") {
        scenario("handle request with valid json") {
            withTestApplication(Application::test) {
                handleRequest(HttpMethod.Post, "/job-states") {
                    addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                    setBody("""["SUCCESS", "SUCCESS"]""")
                }.apply {
                    requestHandled shouldBe true
                    response.status() shouldBe HttpStatusCode.OK
                }
            }
        }

        scenario("handle request") {
            withTestApplication(Application::test) {
                handleRequest(HttpMethod.Post, "/job-states") {
                    addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                    setBody("")
                }.apply {
                    requestHandled shouldBe true
                    response.status() shouldBe HttpStatusCode.OK
                }
            }
        }
    }
})