package com.aoe.ampelmaennchen

import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.host.commandLineEnvironment
import org.jetbrains.ktor.jetty.JettyApplicationHost

fun Application.raspberry() {
    Server(this)
}

fun main(args: Array<String>) {
    val environment = commandLineEnvironment(args)
    JettyApplicationHost(environment).start()
}