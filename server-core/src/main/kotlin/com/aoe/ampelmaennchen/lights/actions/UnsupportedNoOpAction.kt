package com.aoe.ampelmaennchen.lights.actions

import com.aoe.ampelmaennchen.lights.Light

/**
 * A fallback action that does nothing.
 */
class UnsupportedNoOpAction(override val light: Light) : LightActionRunnable {

    override fun run() {
        // no-op
    }
}