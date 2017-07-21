package com.aoe.ampelmaennchen.lights.actions

import java.util.concurrent.Callable

/**
 * A [LightAction] that returns a result.
 *
 * @param T The result type
 */
interface LightActionCallable<T> : Callable<T>, LightAction