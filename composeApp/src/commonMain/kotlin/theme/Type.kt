package theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import haloquiz.composeapp.generated.resources.*
import haloquiz.composeapp.generated.resources.Res
import haloquiz.composeapp.generated.resources.halo
import haloquiz.composeapp.generated.resources.rajdhani_bold
import haloquiz.composeapp.generated.resources.rajdhani_regular
import org.jetbrains.compose.resources.Font

@Composable
fun HaloInfinite() = FontFamily(
    Font(resource = Res.font.rajdhani_regular),
    Font(resource = Res.font.rajdhani_bold, FontWeight.Bold),
    Font(resource = Res.font.rajdhani_medium, FontWeight.Medium),
    Font(resource = Res.font.rajdhani_light, FontWeight.Light),
    Font(resource = Res.font.rajdhani_semibold, FontWeight.SemiBold),
)

@Composable
fun Halo() = FontFamily(
    Font(Res.font.halo),
)

@Composable
fun HaloTypography() = Typography().run {
    val fontFamily = HaloInfinite()
    copy(
        h1 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 100.sp
        ),
        h2 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Light,
            fontSize = 62.sp
        ),
        h3 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 50.sp
        ),
        h4 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 35.sp
        ),
        h5 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 25.sp
        ),
        h6 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 21.sp
        ),
        subtitle1 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp
        ),
        subtitle2 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp
        ),
        body1 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 17.sp
        ),
        body2 = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 15.sp
        ),
        button = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp
        ),
        caption = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        ),
        overline = TextStyle(
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
    )
}
