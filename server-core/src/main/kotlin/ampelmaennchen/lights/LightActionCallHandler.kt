package ampelmaennchen.lights

import ampelmaennchen.lights.actions.LightActionCallable
import ampelmaennchen.lights.actions.LightActionRunnable
import org.jetbrains.ktor.application.ApplicationCall

/**
 * Allows to handle an [LightActionRunnable] triggered by a [ApplicationCall].
 *
 * Implementations are responsible for performing the `action` and responding the `call`
 */
interface LightActionCallHandler {

    /**
     * A call triggered an action.
     *
     * @param call the call that got triggered.
     * @param action the action to be performed
     */
    suspend fun perform(call: ApplicationCall, action: LightActionRunnable)

    /**
     * A call triggered an action.
     *
     * @param call the call that got triggered.
     * @param action the action to be performed
     */
    suspend fun perform(call: ApplicationCall, action: LightActionCallable<out Any>)

}