import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CountDownTimer(
    private val millisInFuture: Long,
    private val countDownInterval: Long,
    private val onTick: (Long) -> Unit,
    private val onFinish: () -> Unit,
    private val coroutineContext: CoroutineContext = Dispatchers.Main
) {
    private var job: Job? = null

    fun start() {
        job = CoroutineScope(coroutineContext).launch {
            var timeLeft = millisInFuture
            while (timeLeft > 0) {
                onTick(timeLeft)
                delay(countDownInterval)
                timeLeft -= countDownInterval
            }
            onFinish()
        }
    }

    fun cancel() {
        job?.cancel()
    }
}