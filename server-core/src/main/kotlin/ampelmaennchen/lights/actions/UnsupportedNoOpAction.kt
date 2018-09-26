package ampelmaennchen.lights.actions

import ampelmaennchen.lights.Light

/**
 * A fallback action that does nothing.
 */
class UnsupportedNoOpAction(override val light: Light) : LightActionRunnable {

    override fun run() {
        // no-op
    }
}