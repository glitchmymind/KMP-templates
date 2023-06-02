import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds

@Suppress("MagicNumber")
@Composable
fun Circle(
    modifier: Modifier = Modifier,
) {
    val contentColor = LocalContentColor.current
    Box(modifier = modifier.clipToBounds().background(contentColor, CircleShape))
}
