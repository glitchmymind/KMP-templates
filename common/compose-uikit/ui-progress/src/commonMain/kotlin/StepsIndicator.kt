@file:SuppressWarnings("detekt.MatchingDeclarationName")

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import theme.UiKitTheme

@Composable
fun StepsIndicator(
    total: Int,
    completed: Int,
    modifier: Modifier = Modifier,
    error: Int = 0,
    defaultColor: Color = UiKitTheme.colors.c4,
    completedColor: Color = UiKitTheme.colors.c9,
    errorColor: Color = UiKitTheme.colors.c8,
    segmentGap: Dp = 4.dp,
) {
    check(error + completed <= total)
    Canvas(modifier = modifier.height(StepsIndicatorTokens.Height)) {
        val segmentGapPx = segmentGap.toPx()
        val segmentWidth = (size.width - (total - 1) * segmentGapPx) / total
        repeat(total) { i ->
            drawRoundRect(
                color =
                    when {
                        i < completed -> completedColor
                        i in completed until completed + error -> errorColor
                        else -> defaultColor
                    },
                topLeft = Offset(x = i * (segmentWidth + segmentGapPx), y = 0f),
                cornerRadius = CornerRadius(size.height / 2f),
                size = Size(width = segmentWidth, height = size.height),
            )
        }
    }
}

object StepsIndicatorTokens {

    val Height = 4.dp
}

//@Preview
@Composable
private fun StepsIndicatorPreview() {
    UiKitTheme {
        Column(modifier = Modifier.width(200.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            StepsIndicator(
                total = 10,
                completed = 5,
                error = 3,
                modifier = Modifier.fillMaxWidth().height(8.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            StepsIndicator(
                total = 4,
                completed = 2,
                error = 1,
                modifier = Modifier.fillMaxWidth().height(4.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            StepsIndicator(
                total = 6,
                completed = 3,
                error = 1,
                modifier = Modifier.fillMaxWidth().height(6.dp),
                completedColor = UiKitTheme.colors.c6,
                errorColor = UiKitTheme.colors.c10,
            )
        }
    }
}
