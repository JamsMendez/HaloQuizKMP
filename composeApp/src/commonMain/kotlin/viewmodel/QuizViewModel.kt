package viewmodel

import SimpleQuiz
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.AnswerModel
import model.QuestionModel

class QuizViewModel(
    private val scope: CoroutineScope
) {
    private val _currentQuestionState: MutableState<QuestionModel> = mutableStateOf(QuestionModel())
    private val _valueDownTimerState: MutableState<Float> = mutableStateOf(1f)
    private val _numQuestionState: MutableState<Int> = mutableStateOf(1)

    val valueDownTimer: State<Float> = _valueDownTimerState
    val currentQuestion: State<QuestionModel> = _currentQuestionState
    val numQuestion: State<Int> = _numQuestionState

    private var quiz: SimpleQuiz? = null

    fun startQuestion() {
        val questions = getQuestions()
        initSimpleQuiz(questions)
    }

    fun onSelectedOption(index: Int) {
        val isCorrect = quiz?.evalAnswerQuestion(index) ?: false
        if (isCorrect) exposeQuestionCorrect(index) else exposeQuestionIncorrect()
        nextQuestion(isCorrect)
    }

    private fun handleDownTimer(v: Float) {
        _valueDownTimerState.value = v
    }

    private fun handleQuestion(question: QuestionModel) {
        _currentQuestionState.value = question
    }

    private fun handleNumQuestion(num: Int = 0) {
        _numQuestionState.value = num
    }

    private fun handleTimeout() {
        exposeQuestionIncorrect()
        nextQuestion(false)
    }

    private fun nextQuestion(isPreviousCorrect: Boolean) {
        CoroutineScope(scope.coroutineContext).launch {
            val timiMillis: Long = if (isPreviousCorrect) (1000 * 2).toLong() else 1000 * 3
            delay(timeMillis = timiMillis)
            handleDownTimer(1f)
            delay(1000)

            quiz?.next()

            val num = quiz?.getNumQuestion() ?: 0
            handleNumQuestion(num)
        }
    }

    private fun exposeQuestionIncorrect() {
        val options = _currentQuestionState.value.options.map { option ->
            if (!option.correct) option.selected = true
            option
        }

        _currentQuestionState.value = _currentQuestionState.value.copy(
            options = options,
            answered = true
        )
    }

    private fun exposeQuestionCorrect(selectedOptIndex: Int) {
        val options = _currentQuestionState.value.options.mapIndexed { index, option ->
            if (index == selectedOptIndex) option.selected = true
            option
        }

        _currentQuestionState.value = _currentQuestionState.value.copy(
            options = options,
            answered = true
        )
    }

    private fun initSimpleQuiz(questions: List<QuestionModel> = listOf()) {
        quiz = SimpleQuiz(
            scope = scope,
            questions = questions,
            onDownTimer = ::handleDownTimer,
            onTimeout = ::handleTimeout,
            onQuestion = ::handleQuestion,
        )

        quiz?.start()
    }

    private fun getQuestions(): List<QuestionModel> {
        // http request using flow
        val questions = listOf(
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

        return questions
    }
}