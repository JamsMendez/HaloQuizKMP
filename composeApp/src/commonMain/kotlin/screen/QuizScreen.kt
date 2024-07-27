package screen

import androidx.compose.runtime.Composable
import component.Quiz
import viewmodel.QuizViewModel

@Composable
fun QuizScreen(
    vm: QuizViewModel,
) {
    val valueTimerDown = vm.valueTimerDown
    val currentQuestion = vm.currentQuestion
    val questionNum = vm.questionNum

    val question = currentQuestion.value

    Quiz(
        timerValue = valueTimerDown.value,
        hasQuestion = question.id.isNotEmpty(),
        questionNumber = questionNum.value,
        question = question,
        onStartClicked = vm::onStartClicked,
        onOptionSelected = vm::onOptionSelected,
    )
}