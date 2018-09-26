package ampelmaennchen.routes

import ampelmaennchen.lights.PedestrianLightControl
import ampelmaennchen.lights.actions.JobStateAction
import ampelmaennchen.lights.actions.UnsupportedNoOpAction
import ampelmaennchen.model.JobState
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.request.receiveText
import io.ktor.routing.Route
import io.ktor.routing.contentType
import io.ktor.routing.post
import io.ktor.routing.route

fun Route.jobStates(pedestrianLightControl: PedestrianLightControl): RouteDescriptor =
        describedParentRoute("Control the lights by providing a list of job states") {
            route("job-states") {
                contentType(ContentType.Application.Json) {

                    val objectMapper = ObjectMapper()

                    post {
                        val body: String = call.receiveText()
                        val jobStates: Array<JobState>? = with(objectMapper) {
                            try {
                                readValue(body)
                            } catch (e: Exception) {
                                null
                            }
                        }

                        when (jobStates) {
                            null -> pedestrianLightControl.actionHandler.perform(call,
                                    UnsupportedNoOpAction(pedestrianLightControl.pedestrianLight))

                            else -> pedestrianLightControl.actionHandler.perform(call,
                                    JobStateAction(pedestrianLightControl.pedestrianLight, jobStates))
                        }
                    }
                }
            }
        }
