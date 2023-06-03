import androidx.compose.animation.core.*
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.geometry.toRect
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.*
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import theme.UiKitTheme
import kotlin.math.roundToInt

/**
 * Draws some skeleton UI which is typically used whilst content is 'loading'.
 *
 * Note that, shimmer coordinates have the same start as the element's one (top-left corner).
 *
 * So if element has some non-default alignment (e.g. Center) in parent and custom width/height, it
 * can lead to unexpected behavior (it can be clipped). To avoid this use [centered] flag.
 *
 * @param centered is used when the element is placed with outside Alignment.Center, and you want
 * the shimmer be centered relative the parent with custom size.
 * @param externalHeight you have to pass it when the height of the element cannot be calculated
 * (element height "wrap content")
 * @param externalWidth you have to pass it when the width of the element cannot be calculated
 * (element width "wrap content")
 * @shape desired shape of the shimmer. Defaults to [RectangleShape]
 */
@Suppress("LongMethod")
//@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.shimmer(
    visible: Boolean,
    alignment: Alignment = Alignment.TopStart,
    shape: Shape = RectangleShape,
    externalWidth: Dp = Dp.Unspecified,
    externalHeight: Dp = Dp.Unspecified,
): Modifier =
    shimmer(visible) { color, shimmerAlpha, contentAlpha, alphaPaint, shimmerProgress ->
        val (externalHeightPx, externalWidthPx) =
            with(LocalDensity.current) {
                externalHeight.takeIf { it != Dp.Unspecified }?.toPx() to
                    externalWidth.takeIf { it != Dp.Unspecified }?.toPx()
            }

        drawWithContent {
            if (contentAlpha.value in 0.01f..0.99f) {
                alphaPaint.alpha = contentAlpha.value
                withLayer(size.toRect(), alphaPaint) { this@drawWithContent.drawContent() }
            } else if (contentAlpha.value >= 0.99f) {
                // If the content alpha is > 99%, draw it with no alpha
                drawContent()
            }

            drawShimmer(
                shape = shape,
                color = color,
                alpha = shimmerAlpha.value,
                alphaPaint = alphaPaint,
                progress = shimmerProgress.value,
                alignment = alignment,
                externalWidthPx = externalWidthPx,
                externalHeightPx = externalHeightPx,
            )
        }
    }

/** Draw line chart shimmer by given points. */
fun Modifier.lineChartShimmer(
    points: List<Float>,
    visible: Boolean,
): Modifier =
    shimmer(visible) { color, shimmerAlpha, contentAlpha, alphaPaint, shimmerProgress ->
        val (minY, maxY) =
            remember {
                var min = Float.MAX_VALUE
                var max = Float.MIN_VALUE
                points.forEach { point -> // TODO: fastForEach
                    min = minOf(point, min)
                    max = maxOf(point, max)
                }
                min to max
            }
        val path = remember { Path() }

        drawWithContent {
            if (contentAlpha.value in 0.01f..0.99f) {
                alphaPaint.alpha = contentAlpha.value
                withLayer(size.toRect(), alphaPaint) { this@drawWithContent.drawContent() }
            } else if (contentAlpha.value >= 0.99f) {
                // If the content alpha is > 99%, draw it with no alpha
                drawContent()
            }

            val scaleX = size.width / (points.indices.last - points.indices.first)
            val scaleY = size.height / (maxY - minY)
            val bottomOffset: Float = size.toRect().bottom + minY * scaleY

            fun drawPath() {
                path.reset()
                points.forEachIndexed { index, point -> // TODO : fastForEachIndexed
                    val actualX = index * scaleX
                    val actualY = point * -scaleY + bottomOffset
                    if (index == 0) {
                        path.moveTo(actualX, actualY)
                    } else {
                        val prevActualX = (index - 1) * scaleX
                        val prevActualY = points[index - 1] * -scaleY + bottomOffset
                        // connection points for curve bezier: (x1,y1), (x2, y2)
                        val x1 = (actualX + prevActualX) / 2
                        val y1 = prevActualY
                        val x2 = x1
                        val y2 = actualY

                        path.cubicTo(x1, y1, x2, y2, actualX, actualY)
                    }
                }
                drawPath(
                    path = path,
                    color = color,
                    alpha = shimmerProgress.value,
                    style = Stroke(width = 4.dp.toPx()),
                )
            }

            drawIntoCanvas {
                if (shimmerAlpha.value in 0.01f..0.99f) {
                    alphaPaint.alpha = shimmerAlpha.value
                    withLayer(size.toRect(), alphaPaint) { drawPath() }
                } else if (shimmerAlpha.value >= 0.99f) {
                    drawPath()
                }
            }
        }
    }

//@SuppressLint("UnnecessaryComposedModifier")
private fun Modifier.shimmer(
    visible: Boolean,
    factory:
        @Composable
        Modifier.(
            color: Color,
            shimmerAlpha: State<Float>,
            contentAlpha: State<Float>,
            alphaPaint: Paint,
            progress: State<Float>,
        ) -> Modifier,
): Modifier = composed {
    val (initialAlpha, targetAlpha) =
        if (isSystemInDarkTheme()) {
            0.8f to 0.5f
        } else {
            1f to 0.6f
        }

    val color = UiKitTheme.colors.shimmer
    var shimmerProgress = remember<State<Float>> { mutableStateOf(0f) }
    val infiniteTransition = rememberInfiniteTransition()

    val transitionState =
        remember { MutableTransitionState(visible) }.apply { targetState = visible }
    val transition = updateTransition(transitionState, "shimmer_label")

    val shimmerAlpha =
        transition.animateFloat(
            label = "shimmer_fade",
            targetValueByState = { shimmerVisible -> if (shimmerVisible) 1f else 0f },
            transitionSpec = { tween() },
        )
    val contentAlpha =
        transition.animateFloat(
            label = "content_fade",
            targetValueByState = { shimmerVisible -> if (shimmerVisible) 0f else 1f },
            transitionSpec = { tween() },
        )

    if (visible) {
        shimmerProgress =
            infiniteTransition.animateFloat(
                initialValue = initialAlpha,
                targetValue = targetAlpha,
                animationSpec =
                    infiniteRepeatable(
                        animation = tween(700, easing = FastOutLinearInEasing),
                        repeatMode = RepeatMode.Reverse,
                    ),
            )
    }

    val alphaPaint = remember { Paint() }

    factory(
        color,
        shimmerAlpha,
        contentAlpha,
        alphaPaint,
        shimmerProgress,
    )

//    factory(
//        color = color,
//        shimmerAlpha = shimmerAlpha,
//        contentAlpha = contentAlpha,
//        alphaPaint = alphaPaint,
//        progress = shimmerProgress,
//    )
}

private inline fun DrawScope.withLayer(
    bounds: Rect,
    paint: Paint,
    drawBlock: DrawScope.() -> Unit,
) = drawIntoCanvas { canvas ->
    canvas.saveLayer(bounds, paint)
    drawBlock()
    canvas.restore()
}

@Suppress("LongParameterList")
private fun DrawScope.drawShimmer(
    shape: Shape,
    color: Color,
    alpha: Float,
    alphaPaint: Paint,
    progress: Float,
    alignment: Alignment,
    externalWidthPx: Float?,
    externalHeightPx: Float?,
) {
    drawIntoCanvas {
        val outlineSize =
            Size(width = externalWidthPx ?: size.width, height = externalHeightPx ?: size.height)
        // TODO optimize Outline allocations later
        val outline =
            shape.createOutline(
                size = outlineSize,
                layoutDirection = layoutDirection,
                density = this,
            )
        val shimmerWidth = outlineSize.width.roundToInt()
        val shimmerHeight = outlineSize.height.roundToInt()
        val offset =
            alignment.align(
                size =
                    IntSize(
                        width = shimmerWidth,
                        height = shimmerHeight,
                    ),
                space =
                    IntSize(
                        width = size.width.roundToInt(),
                        height = size.height.roundToInt(),
                    ),
                layoutDirection = layoutDirection,
            )

        translate(
            left = offset.x.toFloat(),
            top = offset.y.toFloat(),
        ) {
            if (alpha in 0.01f..0.99f) {
                alphaPaint.alpha = alpha
                withLayer(outline.bounds, alphaPaint) {
                    drawOutline(outline, color, alpha = progress)
                }
            } else if (alpha >= 0.99f) {
                drawOutline(outline, color, alpha = progress)
            }
        }
    }
}
