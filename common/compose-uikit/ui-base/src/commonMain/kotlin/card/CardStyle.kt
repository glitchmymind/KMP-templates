import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import theme.UiKitTheme

@Immutable
class CardStyle
private constructor(
    val elevation: Dp,
    val backgroundColor: Color,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CardStyle

        if (elevation != other.elevation) return false
        if (backgroundColor != other.backgroundColor) return false

        return true
    }

    override fun hashCode(): Int {
        var result = elevation.hashCode()
        result = 31 * result + backgroundColor.hashCode()
        return result
    }

    override fun toString(): String {
        return "CardStyle(elevation=$elevation, backgroundColor=$backgroundColor)"
    }

    companion object {

        @Stable
        val Elevated
            @Composable
            @ReadOnlyComposable
            get() =
                CardStyle(
                    elevation = 2.dp,
                    backgroundColor =
                        if (isSystemInDarkTheme()) UiKitTheme.colors.c5 else UiKitTheme.colors.c0,
                )

        @Stable
        val Plain
            @Composable
            @ReadOnlyComposable
            get() =
                CardStyle(
                    elevation = 0.dp,
                    backgroundColor = UiKitTheme.colors.c5,
                )
    }
}
