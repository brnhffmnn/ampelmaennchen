package ampelmaennchen.lights.actions

import ampelmaennchen.lights.Light

/**
 * A generic action container that carries a [Light]
 */
interface LightAction {

    val light: Light

}