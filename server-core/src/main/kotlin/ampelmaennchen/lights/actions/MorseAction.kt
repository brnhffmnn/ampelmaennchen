package ampelmaennchen.lights.actions

import ampelmaennchen.lights.Light
import ampelmaennchen.lights.StatefulLightSwitch
import ampelmaennchen.lights.withState
import kotlinx.coroutines.delay

class MorseAction(private val lightSwitch: StatefulLightSwitch,
                  private val message: String) : LightActionRunnable {

    companion object {
        const val DAH_CHAR = '−'
        const val DIT_CHAR = '·'

        const val DIT_MS = 100
        const val DAH_MS = DIT_MS * 3
        const val PAUSE_CODE_MS = DIT_MS
        const val PAUSE_CHAR_MS = DAH_MS
        const val PAUSE_WORD_MS = DIT_MS * 7

        val ALPHABET = mapOf(
                'A' to "·−",
                'B' to "−···",
                'C' to "−·−·",
                'D' to "−··",
                'E' to "·",
                'F' to "··−·",
                'G' to "−−·",
                'H' to "····",
                'I' to "··",
                'J' to "·−−−",
                'K' to "−·−",
                'L' to "·−··",
                'M' to "−−",
                'N' to "−·",
                'O' to "−−−",
                'P' to "·−−·",
                'Q' to "−−·−",
                'R' to "·−·",
                'S' to "···",
                'T' to "−",
                'U' to "··−",
                'V' to "···−",
                'W' to "·−−",
                'X' to "−··−",
                'Y' to "−·−−",
                'Z' to "−−··"
        )

        val NUMBERS = mapOf(
                '1' to "·−−−−",
                '2' to "··−−−",
                '3' to "···−−",
                '4' to "····−",
                '5' to "·····",
                '6' to "−····",
                '7' to "−−···",
                '8' to "−−−··",
                '9' to "−−−−·",
                '0' to "−−−−−"
        )

        val SYMBOLS = mapOf(
                listOf('À', 'Å') to "·−−·−",
                listOf('Ä') to "·−·−",
                listOf('È') to "·−··−",
                listOf('É') to "··−··",
                listOf('Ö') to "−−−·",
                listOf('Ü') to "··−−",
                listOf('ß') to "···−−··",
                listOf('Ñ') to "−−·−−",
                listOf('?') to "··−−··",
                listOf('@') to "·−−·−·"
        )

        val ALLOWED_CHARACTERS = setOf(' ') + ALPHABET.keys + NUMBERS.keys + SYMBOLS.keys.flatten()
    }

    override val light: Light = lightSwitch

    override suspend fun run() {
        val morseThis = message.toUpperCase()

        morseThis.filterNot { ALLOWED_CHARACTERS.contains(it) }.let {
            require(it.isEmpty()) {
                "The Message contains characters which cannot be morsed: ${it.toCharArray().toSet().joinToString(prefix = "'", postfix = "'")}"
            }
        }

        lightSwitch.withState {
            morseAsync(morseThis)
        }
    }

    private suspend fun morseAsync(message: String) {
        lightSwitch.switchOff()
        delay(PAUSE_WORD_MS)

        message.onEach { msgChar ->
            when (msgChar) {
                ' ' -> {
                    delay(PAUSE_WORD_MS)
                }

                else -> {
                    msgChar.morseCode.onEach { code ->
                        lightSwitch.switchOn()

                        when (code) {
                            DAH_CHAR -> delay(DAH_MS)
                            DIT_CHAR -> delay(DIT_MS)
                        }

                        lightSwitch.switchOff()
                        delay(PAUSE_CODE_MS)
                    }

                    delay(PAUSE_CHAR_MS - PAUSE_CODE_MS)
                }
            }
        }

        delay(PAUSE_WORD_MS)
    }

    private val Char.morseCode: String
        get() = requireNotNull(ALPHABET[this]
                ?: NUMBERS[this]
                ?: SYMBOLS.keys.find { it.contains(this) }?.let { SYMBOLS[it] }) {
            "$this is a not morsable character"
        }
}