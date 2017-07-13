package com.aoe.ampelmaennchen.lights

import com.aoe.ampelmaennchen.lights.actions.Action
import org.jetbrains.ktor.application.ApplicationCall

/**
 * Allows to handle an [Action] triggered by a [ApplicationCall].
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
    suspend fun perform(call: ApplicationCall, action: Action)

}