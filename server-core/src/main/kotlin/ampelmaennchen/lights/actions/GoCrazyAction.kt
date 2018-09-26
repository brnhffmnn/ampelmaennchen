package ampelmaennchen.lights.actions

import ampelmaennchen.lights.Light
import ampelmaennchen.lights.RedGreenLightSwitch
import java.time.Duration
import java.util.*

class GoCrazyAction(private val redGreenLight: RedGreenLightSwitch,
                    duration: Duration? = null) : LightActionRunnable {

    companion object {
        val DEFAULT_DURATION: Duration = Duration.ofSeconds(3)
    }

    override val light: Light = redGreenLight

    private val random = Random()
    private val duration = (duration ?: DEFAULT_DURATION)

    override fun run() {
        require(duration.seconds in 1..30) { "Can only go crazy from 1 to 30 seconds" }

        val greenWasOn = redGreenLight.greenLight.isOn
        val redWasOn = redGreenLight.redLight.isOn

        var anim = duration.toMillis()

        while (anim > 0) {
            randomSwitch()

            val wait = (random.nextInt(35) + 10) * 10
            anim -= wait
            Thread.sleep(wait.toLong())
        }

        when (greenWasOn) {
            true -> redGreenLight.greenLight.switchOn()
            else -> redGreenLight.greenLight.switchOff()
        }

        when (redWasOn) {
            true -> redGreenLight.redLight.switchOn()
            else -> redGreenLight.redLight.switchOff()
        }
    }

    private fun randomSwitch() {
        when (random.nextInt(2)) {
            0 -> redGreenLight.toggle()
            1 -> redGreenLight.greenLight.toggle()
            2 -> redGreenLight.redLight.toggle()
        }
    }
}