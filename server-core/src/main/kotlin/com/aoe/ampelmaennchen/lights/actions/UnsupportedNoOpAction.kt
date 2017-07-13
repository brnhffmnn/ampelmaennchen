package com.aoe.ampelmaennchen.lights.actions

import com.aoe.ampelmaennchen.lights.Light

/**
 * A fallback action that does nothing.
 */
class UnsupportedNoOpAction(override val light: Light) : Action {

    override fun perform() {
        // no-op
    }
}