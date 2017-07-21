package com.aoe.ampelmaennchen.lights.actions

import com.aoe.ampelmaennchen.lights.Light

/**
 * A generic action container that carries a [Light]
 */
interface LightAction {

    val light: Light

}