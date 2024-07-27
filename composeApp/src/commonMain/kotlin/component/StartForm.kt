package component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.QuizHaloTheme
import util.Labels.BTN_START

@Composable
fun StartForm(
  modifier: Modifier = Modifier,
  onStart: () -> Unit = {},
  onRankingClicked: () -> Unit = {}
) {
  Column(
    modifier = modifier,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Logo()
    Spacer(
      modifier = Modifier
        .fillMaxWidth()
        .height(16.dp)
    )
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp)
    ) {
      SimpleButton(
        modifier = Modifier.weight(0.45f),
        text = BTN_START,
        onClick = onStart
      )
    }
  }
}


@Preview
@Composable
fun StartFormPreview() {
  QuizHaloTheme {
    StartForm(
      modifier = Modifier.background(Color.Black)
    )
  }
}