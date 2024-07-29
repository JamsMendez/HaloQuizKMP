import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import component.Quiz
import model.QuestionModel
import viewmodel.QuizViewModel

@Composable
fun QuizScreen(
    vm: QuizViewModel,
) {
    val valueTimerDown: Float by vm.valueDownTimer
    val numQuestion: Int by vm.numQuestion
    val question: QuestionModel by vm.currentQuestion

    Quiz(
        timerValue = valueTimerDown,
        hasQuestion = question.id.isNotEmpty(),
        questionNumber = numQuestion,
        question = question,
        onStartClicked = vm::startQuestion,
        onOptionSelected = vm::onSelectedOption,
    )
}