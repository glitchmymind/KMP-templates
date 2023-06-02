package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import color.*
import color.ProvideUiKitColors


@Composable
fun UiKitTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) DarkColorPalette else LightColorPalette

    ProvideUiKitColors(colors = colors) {
        CompositionLocalProvider(
            LocalBackgroundColor provides UiKitTheme.colors.c0,
            LocalUiKitGradients provides GradientsPalette,
        ) { MaterialTheme(typography = typography, shapes = shapes, content = content) }
    }
}

object UiKitTheme : RippleTheme {

    val colors: UiKitColors
        @Composable @ReadOnlyComposable get() = LocalVividColors.current

    val gradients: UiKitGradients
        @Composable @ReadOnlyComposable get() = LocalUiKitGradients.current

    @Composable
    override fun defaultColor(): Color {
        return colors.c4
    }

    @Composable
    override fun rippleAlpha(): RippleAlpha {
        val alpha = 0.5f
        return RippleAlpha(
            alpha,
            alpha,
            alpha,
            alpha,
        )
    }
}