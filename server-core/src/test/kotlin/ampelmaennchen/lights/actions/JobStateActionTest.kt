package ampelmaennchen.lights.actions

import ampelmaennchen.lights.createTestPedestrianLight
import ampelmaennchen.model.JobState
import ampelmaennchen.model.JobState.*
import io.kotlintest.matchers.shouldBe
import io.kotlintest.properties.forAll
import io.kotlintest.properties.headers
import io.kotlintest.properties.row
import io.kotlintest.properties.table
import io.kotlintest.specs.FreeSpec


class JobStateActionTest : FreeSpec({
    "should control lights based on states" - {
        val table = table(
                headers("states", "expected red", "expected green"),
                row(emptyArray<JobState>(), false, false),
                row(arrayOf(SUCCESS, SUCCESS), false, true),
                row(arrayOf(FAILURE, FAILURE), true, false),
                row(arrayOf(SUCCESS, FAILURE), true, false),
                row(arrayOf(UNSTABLE, SUCCESS), false, true),
                row(arrayOf(UNSTABLE, FAILURE), true, false)
        )

        forAll(table) { states, expectedRed, expectedGreen ->
            (states.joinToString()) {
                with(JobStateAction(createTestPedestrianLight(), states)) {
                    with(call()) {
                        first shouldBe expectedRed
                        second shouldBe expectedGreen
                    }
                }
            }
        }
    }
})