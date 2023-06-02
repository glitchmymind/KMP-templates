package typography

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import fontResources

val Roboto
    @Composable @ReadOnlyComposable get() = FontFamily(
        fontResources("roboto_black.ttf", FontWeight.Black, FontStyle.Normal),
        fontResources("roboto_black.ttf", FontWeight.Black, FontStyle.Normal),
        fontResources("roboto_bold.ttf", FontWeight.Bold, FontStyle.Normal),
        fontResources("roboto_medium.ttf", FontWeight.Medium, FontStyle.Normal),
        fontResources("roboto_regular.ttf", FontWeight.Normal, FontStyle.Normal),
    )

val HeaderXL
    @Composable @ReadOnlyComposable get() = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Black,
        fontSize = 34.sp,
        lineHeight = 40.sp,
    )

val HeaderL
    @Composable @ReadOnlyComposable get() = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Black,
        fontSize = 27.sp,
        lineHeight = 32.sp,
    )

val HeaderM
    @Composable @ReadOnlyComposable get() = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Black,
        fontSize = 22.sp,
        lineHeight = 26.sp,
    )

val HeaderS
    @Composable @ReadOnlyComposable get() = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Black,
        fontSize = 18.sp,
        lineHeight = 22.sp,
    )

val ParagraphM
    @Composable @ReadOnlyComposable get() = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
    )

val ParagraphS
    @Composable @ReadOnlyComposable get() = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
    )

val ParagraphXS
    @Composable @ReadOnlyComposable get() = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 14.sp,
    )

val LabelLarge
    @Composable @ReadOnlyComposable get() = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 22.sp,
    )

val LabelNormal
    @Composable @ReadOnlyComposable get() = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 18.sp,
    )

val LabelSmall
    @Composable @ReadOnlyComposable get() = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp,
    )

val LabelXSmall
    @Composable @ReadOnlyComposable get() = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Bold,
        fontSize = 10.sp,
        lineHeight = 16.sp,
    )

val LabelXXSmall
    @Composable @ReadOnlyComposable get() = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Normal,
        fontSize = 9.sp,
        lineHeight = 16.sp,
    )

val typography
    @Composable @ReadOnlyComposable get() = Typography(
        body1 = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
        ),
        body2 = TextStyle(
            fontFamily = Roboto,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 18.sp,
        ),
        subtitle1 = LabelLarge,
        button = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.W500,
            fontSize = 14.sp,
        ),
        caption = LabelNormal,
    )
