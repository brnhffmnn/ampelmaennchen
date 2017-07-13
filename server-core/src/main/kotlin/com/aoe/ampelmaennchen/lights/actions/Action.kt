package com.aoe.ampelmaennchen.lights.actions

import com.aoe.ampelmaennchen.lights.Light

/**
 * An action that can be performed on a ]
 */
interface Action {

    val light: Light
    fun perform()
}

