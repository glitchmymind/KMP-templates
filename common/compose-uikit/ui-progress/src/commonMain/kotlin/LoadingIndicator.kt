import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

private const val Offset = 4f
private const val OffsetDuration = 800
private const val AlphaDuration = 400

private const val CircleCount = 4

@Composable
fun LoadingIndicator(
    size: LoadingIndicatorSize,
    modifier: Modifier = Modifier,
    dotColor: Color = LocalContentColor.current,
    dotCount: Int = CircleCount,
) {
    val indexes = remember(dotCount) { List(dotCount) { it } }
    val offsetValues = remember { mutableListOf<Float>() }
    val alphaValues = remember { mutableListOf<Float>() }

    indexes.forEach { index ->  // TODO: fastForEach
        var offsetValue by remember { mutableStateOf(-Offset) }
        var alphaValue by remember { mutableStateOf(0f) }
        LaunchedEffect(key1 = Unit) {
            if (index > 0) {
                delay(OffsetDuration / indexes.size * (index + 1L))
            }
            animate(
                initialValue = -Offset,
                targetValue = Offset,
                animationSpec =
                    infiniteRepeatable(
                        animation = tween(durationMillis = OffsetDuration),
                        repeatMode = RepeatMode.Reverse,
                    ),
            ) { value, _ -> offsetValue = value }
        }
        // Have to start one more [LaunchedEffect]
        // cause the first one will never be completed (infiniteRepeatable)
        // until remove Node from composition.
        LaunchedEffect(key1 = Unit) {
            if (index > 0) {
                delay(OffsetDuration / indexes.size * (index + 1L))
            }
            animate(
                initialValue = 0f,
                targetValue = 1f,
                animationSpec = tween(durationMillis = AlphaDuration),
            ) { value, _ -> alphaValue = value }
        }
        offsetValues.add(0, offsetValue)
        alphaValues.add(0, alphaValue)
    }

    CompositionLocalProvider(LocalContentColor provides dotColor) {
        Row(
            modifier = modifier.padding(vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(size.spaceBetweenDots),
        ) {
            indexes.forEach { index ->
                Circle(
                    modifier =
                        Modifier.offset(y = offsetValues[index].dp)
                            .graphicsLayer { alpha = alphaValues[index] }
                            .size(size.dotSize),
                )
            }
        }
    }
}
