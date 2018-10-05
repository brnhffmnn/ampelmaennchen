package ampelmaennchen.lights.actions

import ampelmaennchen.lights.PedestrianLight
import ampelmaennchen.model.JobState

/**
 * Checks the supplied states and triggers the red and green light.
 *
 * The returned [Pair] contains the final states of both light (first=red, second=green)
 */
class JobStateAction(override val light: PedestrianLight,
                     val states: Array<JobState>) : LightActionCallable<Pair<Boolean, Boolean>> {

    private val hasNegativeStates = states.count {
        when (it) {
            JobState.FAILURE, JobState.UNKNOWN -> true
            else -> false
        }
    } > 0

    private val allStatesPositive = states.count {
        when (it) {
            JobState.SUCCESS, JobState.UNSTABLE -> true
            else -> false
        }
    } == states.size

    override suspend fun call(): Pair<Boolean, Boolean> {
        when {
            states.isEmpty() -> {
                light.greenLight.switchOff()
                light.redLight.switchOff()
            }

            allStatesPositive -> {
                light.greenLight.switchOn()
                light.redLight.switchOff()
            }

            hasNegativeStates -> {
                light.greenLight.switchOff()
                light.redLight.switchOn()
            }

            else -> {
                light.greenLight.switchOn()
                light.redLight.switchOn()
            }
        }

        return Pair(light.redLight.isOn, light.greenLight.isOn)
    }

}