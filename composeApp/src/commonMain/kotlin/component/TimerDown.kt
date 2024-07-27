package component

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.BlueHalo
import theme.OrangeHalo
import theme.QuizHaloTheme

@Composable
fun TimerDown(
  modifier: Modifier = Modifier,
  color: Color = BlueHalo,
  value: Float = 1f
) {
  val warningValue = 0.3f

  ConstraintLayout(
    modifier = modifier
      .fillMaxWidth()
      .height(25.dp)
      .background(Color.Transparent)
  ) {
    val (mainBar, warningBar) = createRefs()

    val progressAnimDuration = 900
    val progressMain by animateFloatAsState(
      targetValue = if (value <= warningValue) 0f else value,
      animationSpec = tween(durationMillis = if (value <= warningValue) 500 else progressAnimDuration, easing = FastOutSlowInEasing)
    )

    val progressWarning by animateFloatAsState(
      targetValue = if (value < warningValue) value * (1f / warningValue) else 1f,
      animationSpec = tween(durationMillis = progressAnimDuration, easing = FastOutSlowInEasing)
    )

    if (value <= 0.333) {
      Row(
        modifier = Modifier.fillMaxWidth()
          .constrainAs(warningBar) {
            top.linkTo(parent.top, margin = 13.dp)
          },
        horizontalArrangement = Arrangement.Center
      ) {
        LinearProgressIndicator(
          progress = progressWarning,
          modifier = Modifier
            .fillMaxWidth(0.5f)
            .clip(shape = RoundedCornerShape(25.dp))
            .height(12.dp),
          color = OrangeHalo,
          backgroundColor = OrangeHalo.copy(alpha = 0.4f),
        )
      }
    }

    LinearProgressIndicator(
      progress = progressMain,
      modifier = Modifier
        .fillMaxWidth()
        .clip(shape = RoundedCornerShape(25.dp))
        .height(25.dp)
        .constrainAs(mainBar) {
          top.linkTo(parent.top)
          bottom.linkTo(parent.bottom)
        },
      color = if (value <= warningValue) Color.Red.copy(alpha = 0.8f) else color,
      backgroundColor = if (value > warningValue) Color.Black.copy(alpha = 0.5f) else Color.Red.copy(alpha = 0.4f),
    )

  }
}

@Preview
@Composable
fun TimerDownPreview() {
  QuizHaloTheme {
    TimerDown()
  }
}