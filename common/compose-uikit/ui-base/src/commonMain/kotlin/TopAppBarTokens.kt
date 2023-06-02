import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

object TopAppBarTokens {
    val Height = 56.dp

    val OffsetLimit: Float
        @Composable get() = with(LocalDensity.current) { -Height.toPx() }
}
