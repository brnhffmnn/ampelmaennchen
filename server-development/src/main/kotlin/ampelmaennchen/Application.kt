package ampelmaennchen

import ampelmaennchen.lights.PedestrianLight
import ampelmaennchen.lights.PedestrianLightControl
import org.jetbrains.ktor.application.Application
import org.jetbrains.ktor.host.embeddedServer
import org.jetbrains.ktor.jetty.Jetty
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
            reloadPackages = watches,
            module = Application::development
    ).start()
}