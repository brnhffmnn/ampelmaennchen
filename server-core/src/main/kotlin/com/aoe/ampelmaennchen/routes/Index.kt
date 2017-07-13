package com.aoe.ampelmaennchen.routes

import org.jetbrains.ktor.application.call
import org.jetbrains.ktor.http.ContentType
import org.jetbrains.ktor.response.respondText
import org.jetbrains.ktor.routing.Route
import org.jetbrains.ktor.routing.accept
import org.jetbrains.ktor.routing.get

fun Route.index(vararg routeDescriptors: RouteDescriptor) {
    accept(ContentType.Text.Any) {
        get {
            call.respondText(generateIndex(routeDescriptors.toList()))
        }
    }
}

private fun generateIndex(routeDescriptors: List<RouteDescriptor>) : String {
    val map = mutableMapOf<Route, String>()
    val index: StringBuilder = StringBuilder()

    fun mapRouteDesc(rd: RouteDescriptor) {
        map += rd.route to rd.description
        rd.children.forEach { mapRouteDesc(it) }
    }

    routeDescriptors.forEach { mapRouteDesc(it) }

    val maxRouteLen = map.keys.maxBy { it.toString().length }.toString().length

    map.forEach { (route, desc) ->
        index.append(route)
        repeat(maxRouteLen - route.toString().length) { index.append(" ") }
        index.append(" -> ")
        index.append(desc).append("\n")
    }

    return index.toString()
}

interface RouteDescriptor {
    val description: String
    val route: Route
    val children: List<RouteDescriptor>
}

data class ParentRouteDescriptor(override val description: String,
                                 override val route: Route) : RouteDescriptor {

    override val children: List<RouteDescriptor> = mutableListOf()

    fun withDescripedChild(description: String,
                           routeBuilder: Route.() -> Route): ParentRouteDescriptor {

        (children as MutableList).add(ParentRouteDescriptor(description, route.routeBuilder()))
        return this
    }
}

fun Route.describedRoute(description: String, route: Route.() -> Route): RouteDescriptor {
    return ParentRouteDescriptor(description, route())
}

fun Route.describedParentRoute(description: String, route: Route.() -> Route): ParentRouteDescriptor {
    return ParentRouteDescriptor(description, route())
}