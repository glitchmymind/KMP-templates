import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.unit.dp
import typography.LabelLarge

object MainButtonTokens {

    val TextStyle
        @Composable @ReadOnlyComposable get() = LabelLarge

    val Shape = RoundedCornerShape(12.dp)

    val IconSpacing = 8.dp

    val IconSize = 24.dp

    val MinHeight = 48.dp
}
