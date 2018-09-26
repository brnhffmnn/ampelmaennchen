package ampelmaennchen

import ampelmaennchen.lights.PedestrianLight
import ampelmaennchen.lights.PedestrianLightControl
import io.ktor.application.Application
import io.ktor.server.engine.embeddedServer
import io.ktor.server.jetty.Jetty
import java.nio.file.Paths

fun Application.development() {
    val pedestrianLight = PedestrianLight(DevRedLight(), DevGreenLight())
    val actionHandler = DevLightActionHandler()
    val lightControl = PedestrianLightControl(pedestrianLight, actionHandler)

    Server(this, lightControl)
}

fun main(args: Array<String>) {

    val watches = listOf(Paths.get("").toAbsolutePath().toString(), "server-core", "server-development")

    embeddedServer(
            factory = Jetty,
            port = 8080,
            watchPaths = watches,
            module = Application::development
    ).start()
}