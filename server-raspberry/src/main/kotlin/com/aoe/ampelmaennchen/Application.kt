package com.aoe.ampelmaennchen

import com.aoe.ampelmaennchen.lights.PedestrianLight
import com.aoe.ampelmaennchen.lights.PedestrianLightControl
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.host.commandLineEnvironment
import org.jetbrains.ktor.jetty.JettyApplicationHost

fun Application.raspberry() {
    val pedestrianLight = PedestrianLight(RedLight(), GreenLight())
    val actionHandler = RaspberryLightActionHandler()
    val lightControl = PedestrianLightControl(pedestrianLight, actionHandler)

    Server(this, lightControl)
}

fun main(args: Array<String>) {
    val environment = commandLineEnvironment(args)
    JettyApplicationHost(environment).start()
}