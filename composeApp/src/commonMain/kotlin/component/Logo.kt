package component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import haloquiz.composeapp.generated.resources.Res
import haloquiz.composeapp.generated.resources.halo
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.ui.tooling.preview.Preview
import theme.HaloTypography
import theme.QuizHaloTheme
import util.Labels

@Composable
fun Logo(
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .fillMaxWidth(),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.Center
  ) {
    Text(
      text = Labels.QUIZ,
      modifier = Modifier,
      Color.White,
      style = HaloTypography().h3
    )
    Text(
      text = Labels.HALO,
      modifier = Modifier,
      color = Color.White,
      fontSize = 76.sp,
      fontWeight = FontWeight.Bold,
      fontFamily = FontFamily(
        Font(Res.font.halo),
        Font(Res.font.halo, FontWeight.Bold)
      )
    )
  }
}


@Preview
@Composable
fun LogoPreview() {
  QuizHaloTheme {
    Logo()
  }
}