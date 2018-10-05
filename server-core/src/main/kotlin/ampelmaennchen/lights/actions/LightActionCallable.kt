package ampelmaennchen.lights.actions

/**
 * A [LightAction] that returns a result.
 *
 * @param T The result type
 */
interface LightActionCallable<T> : LightAction {

    suspend fun call(): T
}