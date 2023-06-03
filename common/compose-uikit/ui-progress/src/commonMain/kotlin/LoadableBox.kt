import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import theme.UiKitTheme

@Composable
fun LoadableBox(
    loading: Boolean,
    modifier: Modifier = Modifier,
    showProgressBar: Boolean = true,
    progressModifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val overlayAlpha by animateFloatAsState(if (loading) 1f else 0f)
    Box(
        modifier = modifier,
    ) {
        content()
        if (overlayAlpha > 0.01f) {
            Box(
                modifier =
                    Modifier.fillMaxSize()
                        .graphicsLayer(alpha = overlayAlpha)
                        .background(color = UiKitTheme.colors.progressOverlay)
                        .pointerInput(Unit) { detectTapGestures {} },
                contentAlignment = Alignment.Center,
            ) {
                if (loading && showProgressBar) {
                    CircularProgress(modifier = progressModifier, trackColor = Color.Transparent)
                }
            }
        }
    }
}
