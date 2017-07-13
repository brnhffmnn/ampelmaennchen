package com.aoe.ampelmaennchen.lights

/**
 * A light that is either on or off.
 */
interface Light {

    /**
     * The light's name
     */
    val name: String

    /**
     * Indicates whether the light is currently lit-up or not
     */
    val isOn: Boolean

}