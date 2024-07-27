package component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import haloquiz.composeapp.generated.resources.Res
import haloquiz.composeapp.generated.resources.shotgun
import org.jetbrains.compose.resources.painterResource
import theme.BlueHalo
import theme.HaloTypography
import util.Constants.NUM_QUESTIONS

@Composable
fun Ammunition(
  modifier: Modifier = Modifier,
  value: Int = NUM_QUESTIONS,
  total: Int = NUM_QUESTIONS,
) {
  Row(
    modifier = modifier
      .wrapContentHeight(),
    verticalAlignment = Alignment.CenterVertically
  ) {
    Text(
      text = value.toString().padStart(2, '0'),
      modifier = Modifier.weight(0.25f),
      color = BlueHalo.copy(alpha = 0.5f),
      style = HaloTypography().h4,
      textAlign = TextAlign.Center,
      fontWeight = FontWeight.Bold
    )
    Row(
      modifier = Modifier
        .height(48.dp)
        .weight(0.05f),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Spacer(
        modifier = Modifier
          .height(20.dp)
          .width(1.dp)
          .background(BlueHalo.copy(alpha = 0.5f))
      )
    }
    Text(
      text = total.toString().padStart(2, '0'),
      modifier = Modifier.weight(0.2f),
      color = BlueHalo.copy(alpha = 0.5f),
      style = HaloTypography().h5,
      textAlign = TextAlign.Center,
      fontWeight = FontWeight.SemiBold
    )
    Image(
      painter = painterResource(Res.drawable.shotgun),
      contentDescription = "Ammunition Icon",
      modifier = Modifier
        .height(48.dp)
        .padding(horizontal = 8.dp)
        .weight(0.5f),
      contentScale = ContentScale.Inside,
      alpha = 0.6f
    )
  }
}