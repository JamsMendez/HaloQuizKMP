package component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.BlueHalo
import theme.QuizHaloTheme

@Composable
fun MotionSensor(
  modifier: Modifier = Modifier,
  size: Int = 100
) {
  Row(
    modifier = modifier
      .wrapContentHeight(),
    horizontalArrangement = Arrangement.Start
  ) {
    ConstraintLayout(
      modifier = Modifier
        .clip(CircleShape)
        .size((size).dp)
    ) {
      val radial = Brush.radialGradient(
        listOf(
          BlueHalo.copy(alpha = 0.1f),
          BlueHalo.copy(alpha = 0.25f),
          BlueHalo.copy(alpha = 0.5f)
        )
      )

      val (circleRef, cutCornerRef) = createRefs()

      Box(
        modifier = Modifier
          .size((size).dp)
          .clip(CutCornerShape((size / 2).dp))
          .background(BlueHalo.copy(alpha = 0.4f))
          .constrainAs(cutCornerRef) {
            top.linkTo(circleRef.top, margin = (-(size / 2)).dp)
            start.linkTo(circleRef.start)
            end.linkTo(circleRef.end)
          }
      )

      Box(
        modifier = Modifier
          .size((size).dp)
          .clip(CircleShape)
          .background(radial)
          .border(width = (0.8).dp, color = BlueHalo, shape = CircleShape)
          .constrainAs(circleRef) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
          }
      ) {
        Box(
          modifier = Modifier
            .size((size * 0.75).dp)
            .clip(CircleShape)
            .align(Alignment.Center)
            .border(width = (0.3).dp, color = BlueHalo, shape = CircleShape)
            .background(Color.Transparent)
        ) {
          Box(
            modifier = Modifier
              .size((size * 0.5).dp)
              .clip(CircleShape)
              .align(Alignment.Center)
              .border(width = (0.8).dp, color = BlueHalo, shape = CircleShape)
              .background(Color.Transparent)
          ) {
            Box(
              modifier = Modifier
                .size((size * 0.25).dp)
                .clip(CircleShape)
                .align(Alignment.Center)
                .border(width = (0.3).dp, color = BlueHalo, shape = CircleShape)
                .background(Color.Transparent)
            )
          }
        }
      }
    }
  }
}


@Preview
@Composable
fun MotionSensorPreview() {
  QuizHaloTheme {
    MotionSensor(
      modifier = Modifier.background(Color.Black)
    )
  }
}