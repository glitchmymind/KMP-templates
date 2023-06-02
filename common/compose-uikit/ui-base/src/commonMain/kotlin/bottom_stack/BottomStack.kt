import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import color.LocalBackgroundColor
import preview.PreviewBox

private val FadingHeight = 24.dp
private val FadingSolidHeight = 2.dp
private val HorizontalPadding = 16.dp
private val VerticalPadding = 10.dp

/**
 * A content elements, such as the MainButton, MUST apply elementPadding to its own Modifier to keep
 * right element spacing.
 */
@Composable
fun BottomStack(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.(elementPadding: PaddingValues) -> Unit,
) {
    BottomStackImpl(
        modifier = modifier,
        content = content,
    )
}

@Composable
fun BottomStackWithFade(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.(elementPadding: PaddingValues) -> Unit,
) {
    val bottomStackState = rememberBottomStackState()
    val backgroundColor = LocalBackgroundColor.current

    Column(
        modifier =
            Modifier.fillMaxWidth().drawBehind {
                val fadingHeightPx = FadingHeight.toPx()
                val fadeStartY = 0f
                val alpha = 1f * bottomStackState.decorationsHeightPercentState.value

                drawRect(
                    brush =
                        Brush.verticalGradient(
                            colors = listOf(Color.Transparent, backgroundColor.copy(alpha = alpha)),
                            startY = fadeStartY,
                            endY = fadingHeightPx,
                        ),
                    topLeft = Offset(x = 0f, y = fadeStartY),
                    size = Size(width = size.width, height = fadingHeightPx),
                )
            },
    ) {
        Spacer(
            modifier =
                Modifier.height(
                    FadingHeight * bottomStackState.decorationsHeightPercentState.value,
                ),
        )
        BottomStackImpl(
            modifier = modifier,
            bottomStackState = bottomStackState,
            topPadding = FadingSolidHeight,
            content = content,
        )
    }
}

@Composable
private fun BottomStackImpl(
    modifier: Modifier = Modifier,
    bottomStackState: BottomStackState = rememberBottomStackState(),
    topPadding: Dp = VerticalPadding,
    content: @Composable ColumnScope.(elementPadding: PaddingValues) -> Unit,
) {
    val bottomPadding = VerticalPadding
    val elementPadding = PaddingValues(vertical = BottomStackTokens.ElementVerticalPadding)

    Box(
        Modifier.fillMaxWidth()
            .background(
                LocalBackgroundColor.current.copy(
                    alpha = 1f * bottomStackState.decorationsHeightPercentState.value,
                ),
            ),
    ) {
        Column(modifier = modifier.fillMaxWidth()) {
            Spacer(
                Modifier.height(
                    topPadding * bottomStackState.decorationsHeightPercentState.value,
                ),
            )
            Column(
                modifier =
                    Modifier.fillMaxWidth()
                        .onSizeChanged { size ->
                            bottomStackState.hasContentState.value = size.height > 0
                        }
                        .padding(horizontal = HorizontalPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                content = { content.invoke(this, elementPadding) },
            )
            Spacer(
                Modifier.height(
                    bottomPadding * bottomStackState.decorationsHeightPercentState.value,
                ),
            )
        }
    }
}

//@Preview
@Composable
private fun BottomStackPreview() {
    @Composable
    fun MainButton(modifier: Modifier = Modifier, text: String) {
        Button(
            modifier = modifier.fillMaxWidth(),
            onClick = {},
        ) { Text(text = text) }
    }
    PreviewBox {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            content = {},
            bottomBar = {
                BottomStack { elementPadding ->
                    MainButton(
                        modifier = Modifier.padding(elementPadding),
                        text = "Single button 1",
                    )
                    MainButton(
                        modifier = Modifier.padding(elementPadding),
                        text = "Single button 2",
                    )
                }
            },
        )
    }
}
