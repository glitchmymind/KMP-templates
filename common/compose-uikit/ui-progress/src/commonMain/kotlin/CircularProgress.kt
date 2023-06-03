@file:SuppressWarnings("detekt.MatchingDeclarationName")

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import theme.UiKitTheme
import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.max

private val CircularIndicatorDiameter = 40.dp
// 12 O'clock
private const val DefaultStartAngle = 270f
private const val FullCircleAngle = 360f

private const val RotationsPerCycle = 5
private const val RotationDuration = 1332
private const val StartAngleOffset = -90f
private const val BaseRotationAngle = 286f
private const val JumpRotationAngle = 290f
private const val RotationAngleOffset = (BaseRotationAngle + JumpRotationAngle) % 360f
private const val HeadAndTailAnimationDuration = (RotationDuration * 0.5).toInt()
private const val HeadAndTailDelayDuration = HeadAndTailAnimationDuration

private val CircularEasing = CubicBezierEasing(0.4f, 0f, 0.2f, 1f)

@Composable
fun CircularProgress(
    modifier: Modifier = Modifier,
    strokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth,
    indicatorColor: Color = UiKitTheme.colors.c6,
    trackColor: Color = UiKitTheme.colors.c4,
) {
    val stroke =
        with(LocalDensity.current) {
            Stroke(
                width = strokeWidth.toPx(),
                cap = StrokeCap.Round,
            )
        }

    val transition = rememberInfiniteTransition()

    val currentRotation by
        transition.animateValue(
            initialValue = 0,
            targetValue = RotationsPerCycle,
            typeConverter = Int.VectorConverter,
            animationSpec =
                infiniteRepeatable(
                    animation =
                        tween(
                            durationMillis = RotationDuration * RotationsPerCycle,
                            easing = LinearEasing,
                        ),
                ),
        )
    val baseRotation by
        transition.animateFloat(
            initialValue = 0f,
            targetValue = BaseRotationAngle,
            animationSpec =
                infiniteRepeatable(
                    animation = tween(durationMillis = RotationDuration, easing = LinearEasing),
                ),
        )
    val endAngle by
        transition.animateFloat(
            initialValue = 0f,
            targetValue = JumpRotationAngle,
            animationSpec =
                infiniteRepeatable(
                    animation =
                        keyframes {
                            durationMillis = HeadAndTailAnimationDuration + HeadAndTailDelayDuration
                            0f at 0 with CircularEasing
                            JumpRotationAngle at HeadAndTailAnimationDuration
                        },
                ),
        )
    val startAngle by
        transition.animateFloat(
            initialValue = 0f,
            targetValue = JumpRotationAngle,
            animationSpec =
                infiniteRepeatable(
                    animation =
                        keyframes {
                            durationMillis = HeadAndTailAnimationDuration + HeadAndTailDelayDuration
                            0f at HeadAndTailDelayDuration with CircularEasing
                            JumpRotationAngle at durationMillis
                        },
                ),
        )
    Canvas(modifier = modifier.progressSemantics().size(CircularIndicatorDiameter)) {
        drawCircularIndicator(
            startAngle = startAngle,
            sweep = FullCircleAngle,
            color = trackColor,
            stroke = stroke,
        )
        val currentRotationAngleOffset = currentRotation * RotationAngleOffset % 360f
        val sweep = abs(endAngle - startAngle)
        val offset = StartAngleOffset + currentRotationAngleOffset + baseRotation
        drawIndeterminateCircularIndicator(
            startAngle = startAngle + offset,
            strokeWidth = strokeWidth,
            sweep = sweep,
            color = indicatorColor,
            stroke = stroke,
        )
    }
}

@Composable
fun CircularProgress(
    progress: Float, // TODO: @FloatRange(from = 0.0, to = 1.0)
    modifier: Modifier = Modifier,
    strokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth,
    indicatorColor: Color = UiKitTheme.colors.c6,
    trackColor: Color = UiKitTheme.colors.c4,
    startAngle: Float = DefaultStartAngle,
    direction: CircularProgressDirection = CircularProgressDirection.Clockwise,
) {
    val stroke =
        with(LocalDensity.current) {
            Stroke(
                width = strokeWidth.toPx(),
                cap = StrokeCap.Round,
            )
        }
    Canvas(modifier = modifier.progressSemantics(progress).size(CircularIndicatorDiameter)) {
        val sweep = direction.sign * progress * FullCircleAngle
        drawCircularIndicator(
            startAngle = startAngle,
            sweep = FullCircleAngle,
            color = trackColor,
            stroke = stroke,
        )
        drawCircularIndicator(
            startAngle = startAngle,
            sweep = sweep,
            color = indicatorColor,
            stroke = stroke,
        )
    }
}

enum class CircularProgressDirection(internal val sign: Int) {
    Clockwise(1),
    CounterClockwise(-1)
}

private fun DrawScope.drawIndeterminateCircularIndicator(
    startAngle: Float,
    strokeWidth: Dp,
    sweep: Float,
    color: Color,
    stroke: Stroke,
) {
    val squareStrokeCapOffset =
        (180.0 / PI).toFloat() * (strokeWidth / (CircularIndicatorDiameter / 2)) / 2f

    val adjustedStartAngle = startAngle + squareStrokeCapOffset
    val adjustedSweep = max(sweep, 0.1f)

    drawCircularIndicator(adjustedStartAngle, adjustedSweep, color, stroke)
}

private fun DrawScope.drawCircularIndicator(
    startAngle: Float,
    sweep: Float,
    color: Color,
    stroke: Stroke,
) {
    val diameterOffset = stroke.width / 2
    val arcDimen = size.width - 2 * diameterOffset
    drawArc(
        color = color,
        startAngle = startAngle,
        sweepAngle = sweep,
        useCenter = false,
        topLeft = Offset(diameterOffset, diameterOffset),
        size = Size(arcDimen, arcDimen),
        style = stroke,
    )
}

@Composable
//@Preview(showBackground = true)
//@Preview()
private fun CircularProgressPreview() {
    UiKitTheme {
        CircularProgress(
            progress = 0.75f,
            modifier = Modifier.size(200.dp),
            strokeWidth = 30.dp,
        )
    }
}
