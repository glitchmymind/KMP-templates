@file:Suppress("MatchingDeclarationName")

package color

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

internal val LocalUiKitGradients = staticCompositionLocalOf { GradientsPalette }

internal val GradientsPalette =
    UiKitGradients(
        violet = listOf(Color(0xFF7D33F6), Color(0xFF86A4FF)),
        mango = listOf(Color(0xFFFFC95C), Color(0xFFFFA375)),
        grayTransparent = listOf(Color(0x4D113781), Color(0x338899C6)),
        solidGrayTransparent = listOf(Color(0x33B0B3B8), Color.Transparent),
        hot = listOf(Color(0xFFFF5050), Color(0xFFFFD335)),
        classesHeader = listOf(Color(0xFFFEE5E5), Color(0xFFE5EFFE)),
    )

@Stable
class UiKitGradients(
    violet: List<Color>,
    mango: List<Color>,
    grayTransparent: List<Color>,
    solidGrayTransparent: List<Color>,
    hot: List<Color>,
    classesHeader: List<Color>,
) {
    var violet by mutableStateOf(violet)
        private set
    var mango by mutableStateOf(mango)
        private set
    var grayTransparent by mutableStateOf(grayTransparent)
        private set
    var solidGrayTransparent by mutableStateOf(solidGrayTransparent)
        private set
    var hot by mutableStateOf(hot)
        private set
    var classesHeader by mutableStateOf(classesHeader)
        private set

    fun update(other: UiKitGradients) {
        violet = other.violet
        mango = other.mango
        grayTransparent = other.grayTransparent
        solidGrayTransparent = other.solidGrayTransparent
        hot = other.hot
        classesHeader = other.classesHeader
    }
}
