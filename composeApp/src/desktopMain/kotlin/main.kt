import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import viewmodel.QuizViewModel

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "HaloQuiz",
    ) {
        val scope = rememberCoroutineScope()
        val vm = remember { QuizViewModel(scope) }

        QuizScreen(vm)
    }
}