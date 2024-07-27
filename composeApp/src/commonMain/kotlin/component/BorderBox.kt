package component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import theme.BlueHalo

@Composable
fun BorderBox(
  modifier: Modifier = Modifier,
  content: @Composable () -> Unit
) {
  Box(
    modifier = modifier
      .background(Color.Transparent)
      .border(width = 1.dp, color = Color.White.copy(alpha = 0.8f))
      .padding(4.dp)
  ) {
    Box(
      modifier = Modifier
        .background(Color.Transparent)
        .border(
          width = (1.5).dp,
          color = BlueHalo.copy(alpha = 0.75f),
          shape = RoundedCornerShape(3.dp)
        )
        .padding((1.5).dp)
        .fillMaxSize()
    ) {
      Row(
        modifier = Modifier
          .fillMaxSize()
          .background(Color.Black.copy(alpha = 0.65f)),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
      ) {
        content()
      }
    }
  }
}