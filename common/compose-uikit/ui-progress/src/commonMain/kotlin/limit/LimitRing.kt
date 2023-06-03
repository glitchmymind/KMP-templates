import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ProgressIndicatorDefaults.ProgressAnimationSpec
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import preview.PreviewBox
import theme.UiKitTheme
import typography.HeaderXL
import typography.ParagraphS

@Composable
fun LargeLimitRing(
    progress: Float, //TODO: @FloatRange(from = 0.0, to = 1.0)
    unlimited: Boolean,
    currentValueText: String,
    maxValueText: String,
    modifier: Modifier = Modifier,
    shimmerVisible: Boolean = false,
) {
    Box(
        modifier =
            modifier
                .fillMaxWidth()
                .padding(horizontal = LargeLimitRingTokens.HorizontalPadding)
                .aspectRatio(1f),
    ) {
        if (!shimmerVisible) {
            LimitRing(
                progress = progress,
                modifier = Modifier.fillMaxSize(),
                strokeWidth = LargeLimitRingTokens.StrokeWidth,
                unlimited = unlimited,
            )
        } else {
            CircularProgress(
                progress = 0f,
                modifier = Modifier.fillMaxSize(),
                strokeWidth = LargeLimitRingTokens.StrokeWidth,
                trackColor = UiKitTheme.colors.shimmer,
            )
        }
        Column(
            modifier =
                Modifier.align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(horizontal = LargeLimitRingTokens.ContentPadding),
        ) {
            Text(
                text = currentValueText,
                modifier =
                    Modifier.fillMaxWidth()
                        .shimmer(
                            visible = shimmerVisible,
                            shape = RoundedCornerShape(16.dp),
                            externalWidth = 120.dp,
                            alignment = Alignment.Center,
                        ),
                textAlign = TextAlign.Center,
                style = HeaderXL,
                color = UiKitTheme.colors.c1,
            )
            Spacer(Modifier.height(LargeLimitRingTokens.TextSpacing))
            Text(
                text = maxValueText,
                modifier =
                    Modifier.fillMaxWidth()
                        .shimmer(
                            visible = shimmerVisible,
                            shape = RoundedCornerShape(8.dp),
                            externalWidth = 80.dp,
                            alignment = Alignment.Center,
                        ),
                style = ParagraphS,
                textAlign = TextAlign.Center,
                color = UiKitTheme.colors.c2,
            )
        }
    }
}

@Composable
fun CellLimitRing(
    progress: Float,  //TODO: @FloatRange(from = 0.0, to = 1.0)
    unlimited: Boolean,
    modifier: Modifier = Modifier,
) {
    LimitRing(
        progress = progress,
        unlimited = unlimited,
        strokeWidth = CellLimitRingTokens.StrokeWidth,
        modifier = modifier.size(CellLimitRingTokens.Size),
    )
}

@Composable
private fun LimitRing(
    progress: Float, //TODO: @FloatRange(from = 0.0, to = 1.0)
    strokeWidth: Dp,
    unlimited: Boolean,
    modifier: Modifier,
) {
    var progressState by rememberSaveable { mutableStateOf(0f) }
    val progressAnimated by
        animateFloatAsState(
            targetValue = progressState,
            animationSpec = ProgressAnimationSpec,
        )
    SideEffect { progressState = progress }
    CircularProgress(
        progress = progressAnimated,
        modifier = modifier,
        strokeWidth = strokeWidth,
        indicatorColor = if (unlimited) UiKitTheme.colors.c4 else UiKitTheme.colors.c6,
        trackColor = UiKitTheme.colors.c4,
    )
}

//@Preview
//@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun CellLimitRingsPreview() {
    PreviewBox {
        Column {
            CellLimitRing(progress = 1f, unlimited = true)
            CellLimitRing(progress = 1f, unlimited = false)
            CellLimitRing(progress = 0.75f, unlimited = false)
            CellLimitRing(progress = 0.5f, unlimited = false)
            CellLimitRing(progress = 0.25f, unlimited = false)
            CellLimitRing(progress = 0.1f, unlimited = false)
            CellLimitRing(progress = 0f, unlimited = false)
        }
    }
}

//@Preview
//@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun LargeCellLimitRingsPreview() {
    PreviewBox {
        Column(Modifier.width(300.dp)) {
            LargeLimitRing(
                progress = 1f,
                unlimited = false,
                currentValueText = "200 €",
                maxValueText = "of 200 € left",
            )
            LargeLimitRing(
                progress = 0.25f,
                unlimited = false,
                currentValueText = "200 €",
                maxValueText = "of 200 € left",
            )
            LargeLimitRing(
                progress = 0.1f,
                unlimited = false,
                currentValueText = "200 €",
                maxValueText = "of 200 € left",
            )
            LargeLimitRing(
                progress = 0f,
                unlimited = false,
                currentValueText = "0 €",
                maxValueText = "of 200 € left",
            )
        }
    }
}
