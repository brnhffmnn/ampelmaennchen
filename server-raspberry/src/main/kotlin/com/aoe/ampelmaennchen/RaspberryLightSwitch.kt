package com.aoe.ampelmaennchen

import com.aoe.ampelmaennchen.lights.LightSwitch

abstract class RaspberryLightSwitch : LightSwitch {

    override val isOn: Boolean
        get() = TODO("need add pi2j")

    override fun switchOn(): Boolean {
        TODO("need add pi2j")
    }

    override fun switchOff(): Boolean {
        TODO("need add pi2j")
    }

}