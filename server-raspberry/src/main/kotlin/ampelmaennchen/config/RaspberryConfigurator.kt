package ampelmaennchen.config

import io.ktor.config.ApplicationConfig

/**
 * Reads configuration and provides access to hardware features
 */
class RaspberryConfigurator(applicationConfig: ApplicationConfig) {

    private val config = applicationConfig.config("raspberry")


    /**
     * Get the configuration for a named pin.
     *
     * @throws [InvalidCongurationException] expected keys/values aren't present or in of invalid
     *         format
     */
    fun getPinConfiguration(name: String): PinConfig {
        val pinNumber: Int
        val startValue: Int

        try {
            val pinConfig = config.config("pin.$name")
            pinNumber = pinConfig.property("number").getString().toInt()
            startValue = pinConfig.property("start-value").getString().toInt()
        } catch (e: Exception) {
            throw InvalidCongurationException(e)
        }

        if (pinNumber !in 0..31) throw InvalidCongurationException("Expecting pin number for '$name' to be in range (0,31)")
        if (startValue !in 0..1) throw InvalidCongurationException("Expecting pin start-value for '$name' to be one of [0,1]")

        return PinConfig(name, pinNumber, startValue)
    }

}