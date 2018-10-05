package ampelmaennchen.lights.actions

/**
 * A [LightAction] that can be run on a light
 */
interface LightActionRunnable : LightAction {

    suspend fun run()
}

