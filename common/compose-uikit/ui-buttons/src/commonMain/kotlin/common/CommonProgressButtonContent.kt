import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha

@Composable
internal fun CommonProgressButtonContent(
    progress: Boolean,
    indicatorSize: LoadingIndicatorSize,
    content: @Composable () -> Unit,
) {
    Box(contentAlignment = Alignment.Center) {
        Row(
            modifier = Modifier.alpha(alpha = if (progress) 0f else 1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) { content.invoke() }
        if (progress) {
            LoadingIndicator(size = indicatorSize)
        }
    }
}
