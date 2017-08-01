package com.aoe.ampelmaennchen.routes

import com.aoe.ampelmaennchen.lights.PedestrianLightControl
import com.aoe.ampelmaennchen.lights.actions.JobStateAction
import com.aoe.ampelmaennchen.lights.actions.UnsupportedNoOpAction
import com.aoe.ampelmaennchen.model.JobState
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.application.receive
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.routing.post
import org.jetbrains.ktor.routing.requestContentType
import org.jetbrains.ktor.routing.route

fun Route.jobStates(pedestrianLightControl: PedestrianLightControl): RouteDescriptor =
        describedParentRoute("Control the lights by providing a list of job states") {
            route("job-states") {
                requestContentType(ContentType.Application.Json) {
                    post {
                        val body: String = call.request.receive()
                        val jobStates: Array<JobState>? = with(jacksonObjectMapper()) {
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
