import CommonButtonsTokens.DisabledAlpha
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import theme.UiKitTheme

@Immutable
class MainButtonStyle
private constructor(
    val backgroundColor: Color,
    val disabledBackgroundColor: Color,
    val contentColor: Color,
) {

    @Composable
    internal fun buttonColors(): ButtonColors =
        ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            disabledBackgroundColor = disabledBackgroundColor,
            contentColor = contentColor,
            disabledContentColor = contentColor.copy(alpha = DisabledAlpha),
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MainButtonStyle

        if (backgroundColor != other.backgroundColor) return false
        if (disabledBackgroundColor != other.disabledBackgroundColor) return false
        if (contentColor != other.contentColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = backgroundColor.hashCode()
        result = 31 * result + disabledBackgroundColor.hashCode()
        result = 31 * result + contentColor.hashCode()
        return result
    }

    override fun toString(): String {
        return "MainButtonStyle(" +
            "backgroundColor=$backgroundColor, " +
            "disabledBackgroundColor=$disabledBackgroundColor, " +
            "contentColor=$contentColor" +
            ")"
    }

    companion object {

        @Stable
        val Primary
            @Composable
            @ReadOnlyComposable
            get() =
                MainButtonStyle(
                    backgroundColor = UiKitTheme.colors.c6,
                    disabledBackgroundColor = UiKitTheme.colors.c6.copy(alpha = DisabledAlpha),
                    contentColor = UiKitTheme.colors.c11,
                )

        @Stable
        val PrimaryAlternative
            @Composable
            @ReadOnlyComposable
            get() =
                MainButtonStyle(
                    backgroundColor = UiKitTheme.colors.c1,
                    disabledBackgroundColor = UiKitTheme.colors.c1.copy(alpha = DisabledAlpha),
                    contentColor = UiKitTheme.colors.c0,
                )

        @Stable
        val Secondary
            @Composable
            @ReadOnlyComposable
            get() =
                MainButtonStyle(
                    backgroundColor = UiKitTheme.colors.c5,
                    disabledBackgroundColor = UiKitTheme.colors.c5.copy(alpha = DisabledAlpha),
                    contentColor = UiKitTheme.colors.c6,
                )

        @Stable
        val Tertiary
            @Composable
            @ReadOnlyComposable
            get() =
                MainButtonStyle(
                    backgroundColor = Color.Transparent,
                    disabledBackgroundColor = Color.Transparent,
                    contentColor = UiKitTheme.colors.c6,
                )

        @Stable
        val Container
            @Composable
            @ReadOnlyComposable
            get() =
                MainButtonStyle(
                    backgroundColor = UiKitTheme.colors.c4,
                    disabledBackgroundColor = UiKitTheme.colors.c4.copy(alpha = DisabledAlpha),
                    contentColor = UiKitTheme.colors.c6,
                )

        @Stable
        val Destructive
            @Composable
            @ReadOnlyComposable
            get() =
                MainButtonStyle(
                    backgroundColor = UiKitTheme.colors.c5,
                    disabledBackgroundColor = UiKitTheme.colors.c5.copy(alpha = DisabledAlpha),
                    contentColor = UiKitTheme.colors.c8,
                )
    }
}
