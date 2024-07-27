package viewmodel

import CountDownTimer
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.AnswerModel
import model.QuestionModel
import util.Constants.NUM_QUESTIONS
import util.Constants.TOTAL_TIMER

class QuizViewModel(
    val scope: CoroutineScope
) {
    private val _questionListState: MutableState<QuestionListState> =
        mutableStateOf(QuestionListState())
    private val _currentQuestionState: MutableState<QuestionModel> = mutableStateOf(QuestionModel())
    private val _valueTimerDownState: MutableState<Float> = mutableStateOf(1f)
    private val _questionNumState: MutableState<Int> = mutableStateOf(NUM_QUESTIONS)

    private var _scorePoints: Int = 0
    private var _points: Int = 0
    private var _questionIndex: Int = -1

    val currentQuestion: State<QuestionModel> = _currentQuestionState
    val valueTimerDown: State<Float> = _valueTimerDownState
    val questionNum: State<Int> = _questionNumState

    private val _countDownTimer = CountDownTimer(
        millisInFuture = 10000L,
        countDownInterval = 1000L,
        onTick = { timeLeft ->
            val points = (timeLeft / 1000)
            val value = points.toFloat() / TOTAL_TIMER
            _valueTimerDownState.value = value
            _points = points.toInt()
        },
        onFinish = {
            exposeUnansweredQuestion()
            restartTimerDown(false)
        },
        coroutineContext = scope.coroutineContext,
    )

    private fun updateNextQuestion(): Boolean {
        val questions = _questionListState.value.questions
        val size = questions.size
        if (size >= NUM_QUESTIONS) {
            val question = questions[_questionIndex]
            val options = question.options.toMutableList().shuffled()
            question.options = options

            _questionNumState.value = NUM_QUESTIONS - _questionIndex
            _currentQuestionState.value = question
            return true
        }

        return false
    }

    fun onStartClicked() {
        _questionIndex += 1

        _questionListState.value = QuestionListState(
            questions = listOf(
                QuestionModel(
                    id = "1",
                    content = "¿Cómo se llama la famosa misión de halo 3 en la que los scarabs son los grandes protagonistas?",
                    options = listOf(
                        AnswerModel("Sierra 117", false),
                        AnswerModel("El Arca", true),
                        AnswerModel("Covenant", false),
                    )
                ),
                QuestionModel(
                    id = "2",
                    content = "Completa la fecha: ${"Cuando me necesites ..."}",
                    options = listOf(
                        AnswerModel("despierame", true),
                        AnswerModel("llamame", false),
                        AnswerModel("ahi estare", false),
                    )
                ),
                QuestionModel(
                    id = "3",
                    content = "¿Enemigo final de la saga de Halo 3?",
                    options = listOf(
                        AnswerModel("La Supermind", false),
                        AnswerModel("El Didacta", false),
                        AnswerModel("La Gravemind", true),
                    )
                ),
            )
        )

        val questions = _questionListState.value.questions.shuffled()
        _questionListState.value = QuestionListState(questions = questions)

        if (updateNextQuestion()) {
            runBlocking {
                _countDownTimer.start()
            }
        }
    }

    fun onOptionSelected(index: Int, isCorrect: Boolean) {
        if (_valueTimerDownState.value < 1f) {
            if (isCorrect) _scorePoints += _points

            exposeQuestionResult(index)
            restartTimerDown(isCorrect)
        }
    }

    private fun restartTimerDown(isCorrect: Boolean) {
        _countDownTimer.cancel()

        _valueTimerDownState.value = 1f

        CoroutineScope(scope.coroutineContext).launch {
            val timiMillis: Long = if (isCorrect) (1000 * 2).toLong() else 1000 * 3
            delay(timeMillis = timiMillis)

            _questionIndex += 1

            if (_questionIndex < NUM_QUESTIONS) {
                if (updateNextQuestion()) _countDownTimer.start()
            }

            if (_questionIndex == NUM_QUESTIONS) {
                delay(1000L)

                _scorePoints = 0
                _points = 0
                _questionIndex = -1

                _valueTimerDownState.value = 1f
                _currentQuestionState.value = QuestionModel()
            }
        }
    }

    private fun exposeQuestionResult(selectedIndex: Int) {
        val question = _currentQuestionState.value

        _currentQuestionState.value = QuestionModel(
            id = question.id,
            content = question.content,
            options = question.options.mapIndexed { index, answer ->
                if (index == selectedIndex) answer.selected = true
                answer
            },
            answered = true
        )
    }

    private fun exposeUnansweredQuestion() {
        val question = _currentQuestionState.value

        _currentQuestionState.value = QuestionModel(
            id = question.id,
            content = question.content,
            options = question.options.map { answer ->
                if (!answer.correct) answer.selected = true
                answer
            },
            answered = true
        )
    }
}