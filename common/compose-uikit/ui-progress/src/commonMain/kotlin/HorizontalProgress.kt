@file:SuppressWarnings("detekt.MatchingDeclarationName")

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.progressSemantics
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.unit.dp
import theme.UiKitTheme

/**
 * [progressOffsets] — offsets within the range of 0f..1f that should be painted with corresponding
 * color from [indicatorColors], e.g. [0.3f, 0.6f, 1f] will result in [0f..0.3f] painted with first
 * color, [0.3f..0.6f] painted with second color, [0.6f..1f] painted with third color
 */
@Composable
fun HorizontalProgress(
    progressOffsets: List<Float>,
    indicatorColors: List<Color>,
    modifier: Modifier = Modifier,
    trackColor: Color = UiKitTheme.colors.c4,
) {
    check(progressOffsets.size == indicatorColors.size)
    Canvas(modifier = modifier.height(HorizontalProgressTokens.Height).progressSemantics()) {
        val strokeWidth = size.height
        drawLinearIndicator(
            startFraction = 0f,
            endFraction = 1f,
            color = trackColor,
            strokeWidth = strokeWidth,
        )
        progressOffsets.forEachIndexed { i, offset -> // TODO: fastForEachIndexed
            drawLinearIndicator(
                startFraction = if (i == 0) 0f else progressOffsets[i - 1],
                endFraction = offset,
                color = indicatorColors[i],
                strokeWidth = strokeWidth,
            )
        }
    }
}

@Composable
fun HorizontalProgress(
    progress: Float, // TODO: @FloatRange(from = 0.0, to = 1.0)
    modifier: Modifier = Modifier,
    indicatorColor: Color = UiKitTheme.colors.c6,
    trackColor: Color = UiKitTheme.colors.c4,
) {
    Canvas(
        modifier = modifier.height(HorizontalProgressTokens.Height).progressSemantics(progress),
    ) {
        val strokeWidth = size.height
        drawLinearIndicator(
            startFraction = 0f,
            endFraction = 1f,
            color = trackColor,
            strokeWidth = strokeWidth,
        )
        drawLinearIndicator(
            startFraction = 0f,
            endFraction = progress,
            color = indicatorColor,
            strokeWidth = strokeWidth,
        )
    }
}

object HorizontalProgressTokens {

    val Height = 6.dp
}

private fun DrawScope.drawLinearIndicator(
    startFraction: Float,
    endFraction: Float,
    color: Color,
    strokeWidth: Float,
) {
    val height = size.height
    val horizontalOffset = height / 2
    val width = size.width - height
    val yOffset = height / 2

    val barStart = horizontalOffset + startFraction * width
    val barEnd = horizontalOffset + endFraction * width

    if (barStart != barEnd) {
        drawLine(
            color = color,
            start = Offset(barStart, yOffset),
            end = Offset(barEnd, yOffset),
            strokeWidth = strokeWidth,
            cap = StrokeCap.Round,
        )
    }
}
