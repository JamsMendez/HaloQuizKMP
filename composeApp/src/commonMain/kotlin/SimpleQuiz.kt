import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import model.QuestionModel

interface DownTimer {
    fun start()
    fun cancel()
}

class SimpleQuiz(
    private val scope: CoroutineScope,
    private val timerPerAsk: Long = 10000L,
    private val questions: List<QuestionModel>,
    private val onDownTimer: (Float) -> Unit,
    private val onTimeout: () -> Unit,
    private val onQuestion: (QuestionModel) -> Unit,
) {
    private val timerInterval = 1000L

    private var currentQuestionIndex = 0
    private var currentPoint = 0
    private var score = 0

    private val timer = CountDownTimer(
        millisInFuture = timerPerAsk,
        countDownInterval = timerInterval,
        onTick = { timeLeft ->
            val value = (timeLeft - timerInterval) / timerInterval
            currentPoint = value.toInt()
            onDownTimer(value.toFloat() / 10)
        },
        onFinish = {
            onTimeout()
        },
        coroutineContext = scope.coroutineContext,
    )

    fun start() {
        if (currentQuestionIndex < questions.size) {
            val question = questions.getOrElse(currentQuestionIndex) { return }
            askQuestion(question)
            scope.launch {
                delay(500)
                timer.start()
            }
        }
    }

    fun next() {
        timer.cancel()
        currentQuestionIndex++

        if (currentQuestionIndex < questions.size) {
            val question = questions.getOrElse(currentQuestionIndex) { return }
            askQuestion(question)
            timer.start()
        } else {
            currentQuestionIndex--
        }
    }

    fun getNumQuestion(): Int {
        return currentQuestionIndex + 1
    }

    fun evalAnswerQuestion(selectedOptIndex: Int): Boolean {
        timer.cancel()

        val question = questions.getOrElse(currentQuestionIndex) { return false }
        val option = question.options.getOrElse(selectedOptIndex) { return false }

        // update score and reset currentPoint
        if (option.correct) score += currentPoint
        currentPoint = 0

        return option.correct
    }

    private fun askQuestion(question: QuestionModel) {
        val options = question.options.toMutableList().shuffled()
        question.options = options
        onQuestion(question)
    }
}