import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CountDownTimer(
    private val millisInFuture: Long,
    private val countDownInterval: Long,
    private val onTick: (Long) -> Unit,
    private val onFinish: () -> Unit,
    private val coroutineContext: CoroutineContext = Dispatchers.Main
) : DownTimer {
    private var job: Job? = null

    override fun start() {
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

    override fun cancel() {
        job?.cancel()
    }
}