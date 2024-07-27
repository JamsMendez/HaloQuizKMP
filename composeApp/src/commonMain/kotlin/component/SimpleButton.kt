package component

import androidx.compose.foundation.BorderStroke
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.HaloTypography

@Composable
fun SimpleButton(
  modifier: Modifier = Modifier,
  text: String = "Button",
  lightMode: Boolean = false,
  onClick: () -> Unit = {},
) {
  OutlinedButton(
    onClick = onClick,
    modifier = modifier,
    colors = ButtonDefaults.buttonColors(
      backgroundColor = if (lightMode) Color.White else Color.Transparent,
      contentColor = if (lightMode) Color.Black else Color.White,
      disabledBackgroundColor = Color.Transparent,
    ),
    border = BorderStroke(
      (1.5).dp,
      Color.White.copy(alpha = 0.75f)
    )
  ) {
    Text(
      text = text,
      modifier = Modifier,
      if (lightMode) Color.Black else Color.White,
      fontSize = 24.sp,
      style = HaloTypography().button
    )
  }
}