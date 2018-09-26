package com.aoe.ampelmaennchen.lights.actions

import com.aoe.ampelmaennchen.lights.testRedGreenLightSwitch
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.properties.forAll
import io.kotlintest.properties.headers
import io.kotlintest.properties.row
import io.kotlintest.properties.table
import io.kotlintest.specs.FreeSpec
import java.time.Duration

class GoCrazyActionTest : FreeSpec({
    "should go crazy within bounds" - {

        val table = table(
                headers("duration", "aborts with exception"),
                row(Duration.ofSeconds(1), false),
                row(Duration.ofSeconds(30), false),
                row(Duration.ofSeconds(0), true),
                row(Duration.ofSeconds(-1), true),
                row(Duration.ofSeconds(31), true)
        )

        forAll(table) { duration, exception ->
            ("crazy ${duration.seconds}s is ${if (!exception) "allowed" else "not allowed"}") {

                val testLight = testRedGreenLightSwitch()

                if (exception) {
                    shouldThrow<IllegalArgumentException> {
                        GoCrazyAction(testLight, duration).run()
                    }
                } else {
                    GoCrazyAction(testLight, duration).run()
                }
            }
        }
    }
})