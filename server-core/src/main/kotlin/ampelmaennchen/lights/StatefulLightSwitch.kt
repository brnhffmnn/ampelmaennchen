package ampelmaennchen.lights

interface StatefulLightSwitch : LightSwitch {

    fun saveState(): Int
    fun restoreState(savepoint: Int)

    /**
     * A stack implementation for managing states.
     */
    class StateStack<T> {

        private val stack = mutableListOf<T>()

        fun isEmpty() = stack.isEmpty()

        /**
         * Push a new state onto the stack.
         *
         * @param state the state to push
         * @return the savepoint needed to restore the state via [popUntil]
         */
        fun push(state: T): Int {
            val position = stack.size
            stack.add(position, state)
            return position
        }

        /**
         * Pops all states from the stack until the supplied savepoint.
         *
         * @param savepoint the savepoint acquired from [push]
         * @return a state
         */
        fun popUntil(savepoint: Int): T {
            check(stack.isNotEmpty()) { "Cannot pop from empty stack" }

            require(savepoint in 0 until stack.size) {
                "Savepoint $savepoint is not within [0, ${stack.size - 1}]"
            }

            return ((stack.size - 1) downTo savepoint)
                    .map { stack.removeAt(it) }
                    .last()
        }
    }
}

suspend fun <T> StatefulLightSwitch.withState(block: suspend StatefulLightSwitch.() -> T): T {
    val savepoint = saveState()
    return try {
        block()
    } finally {
        restoreState(savepoint)
    }
}