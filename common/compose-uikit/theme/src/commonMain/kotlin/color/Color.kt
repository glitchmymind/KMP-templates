@file:Suppress("MatchingDeclarationName")

package color

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

/** Custom colors for UiKit theme (custom attributes) */
@SuppressWarnings("LongParameterList", "ConstructorParameterNaming", "VariableNaming")
@Stable
class UiKitColors(
    c0: Color,
    c1: Color,
    c2: Color,
    c3: Color,
    c4: Color,
    c5: Color,
    c6: Color,
    c6_t: Color,
    c7: Color,
    c7_t: Color,
    c8: Color,
    c9: Color,
    c10: Color,
    c11: Color,
    c12: Color,
    c13: Color,
    c14: Color,
    c16: Color,
    c17: Color,
    c18: Color,
    shimmer: Color,
    progressOverlay: Color,
) {
    var c0 by mutableStateOf(c0)
        private set
    var c1 by mutableStateOf(c1)
        private set
    var c2 by mutableStateOf(c2)
        private set
    var c3 by mutableStateOf(c3)
        private set
    var c4 by mutableStateOf(c4)
        private set
    var c5 by mutableStateOf(c5)
        private set
    var c6 by mutableStateOf(c6)
        private set
    var c6_t by mutableStateOf(c6_t)
        private set
    var c7 by mutableStateOf(c7)
        private set
    var c7_t by mutableStateOf(c7_t)
        private set
    var c8 by mutableStateOf(c8)
        private set
    var c9 by mutableStateOf(c9)
        private set
    var c10 by mutableStateOf(c10)
        private set
    var c11 by mutableStateOf(c11)
        private set
    var c12 by mutableStateOf(c12)
        private set
    var c13 by mutableStateOf(c13)
        private set
    var c14 by mutableStateOf(c14)
        private set
    var c16 by mutableStateOf(c16)
        private set
    var c17 by mutableStateOf(c17)
        private set
    var c18 by mutableStateOf(c18)
        private set

    var shimmer by mutableStateOf(shimmer)
        private set
    var progressOverlay by mutableStateOf(progressOverlay)
        private set

    fun update(other: UiKitColors) {
        c0 = other.c0
        c1 = other.c1
        c2 = other.c2
        c3 = other.c3
        c4 = other.c4
        c5 = other.c5
        c6 = other.c6
        c6_t = other.c6_t
        c7 = other.c7
        c7_t = other.c7_t
        c8 = other.c8
        c9 = other.c9
        c10 = other.c10
        c11 = other.c11
        c12 = other.c12
        c13 = other.c13
        c14 = other.c14
        c16 = other.c16
        c17 = other.c17
        c18 = other.c18
        shimmer = other.shimmer
        progressOverlay = other.progressOverlay
    }
}

@Suppress("ComplexMethod")
fun UiKitColors.findColor(key: String): Color {
    return when (key.lowercase()) {
        "c0" -> c0
        "c1" -> c1
        "c2" -> c2
        "c3" -> c3
        "c4" -> c4
        "c5" -> c5
        "c6" -> c6
        "c6_t" -> c6_t
        "c7" -> c7
        "c7_t" -> c7_t
        "c8" -> c8
        "c9" -> c9
        "c10" -> c10
        "c11" -> c11
        "c12" -> c12
        "c13" -> c13
        "c14" -> c14
        "c16" -> c16
        "c17" -> c17
        "c18" -> c18
        "shimmer" -> shimmer
        else -> throw IllegalArgumentException("")
    }
}

@Composable
internal fun ProvideUiKitColors(colors: UiKitColors, content: @Composable () -> Unit) {
    val colorPalette = remember { colors }
    colorPalette.update(colors)
    CompositionLocalProvider(LocalVividColors provides colorPalette, content = content)
}

internal val LocalVividColors =
    staticCompositionLocalOf<UiKitColors> { error("No ColorPalette provided") }

/**
 * CompositionLocal is used to pass background color down the tree.
 *
 * This value is using for implicit setting background color for surfaces or its decorations such as
 * [FadingEdge].
 *
 * Don't forget to update the value when BottomSheet is opened. Its background color differs from
 * the main screen so we need to reflect it.
 */
val LocalBackgroundColor = staticCompositionLocalOf { Color.Black }
