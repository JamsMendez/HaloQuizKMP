package component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import util.Constants.NUM_QUESTIONS

@Composable
fun QuizFooter(
  modifier: Modifier = Modifier,
  questionNumber: Int = NUM_QUESTIONS
) {
  Row(
    modifier = modifier
      .height(135.dp)
      .background(
        brush = Brush.verticalGradient(
          colors = listOf(
            Color.Transparent,
            Color(0xFF013245).copy(alpha = 0.85f),
          )
        )
      ),
    verticalAlignment = Alignment.CenterVertically
  ) {
    MotionSensor(
      modifier = Modifier
        .padding(start = 8.dp)
        .weight(0.4f)
    )
    Spacer(
      modifier = Modifier
        .height(100.dp)
        .weight(0.1f)
    )
    Ammunition(
      modifier = Modifier
        .weight(0.5f),
      value = questionNumber
    )
  }
}