package ampelmaennchen

import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.timeunit.TimeUnit
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume

class TestCoroutineDispatcher : CoroutineDispatcher(), Delay {

    override fun dispatch(context: CoroutineContext, block: Runnable) {
        block.run()
    }

    override fun scheduleResumeAfterDelay(time: Long, unit: TimeUnit, continuation: CancellableContinuation<Unit>) {
        continuation.resume(Unit)
    }
}