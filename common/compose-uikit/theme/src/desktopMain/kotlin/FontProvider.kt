import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.platform.Font
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

@Composable
@ReadOnlyComposable
actual fun fontResources(
    font: String,
    weight: FontWeight,
    style: FontStyle
): Font = Font("font/$font", weight, style)