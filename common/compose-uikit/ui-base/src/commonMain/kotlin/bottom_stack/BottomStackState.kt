import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.*

@Stable
@Suppress("UseDataClass")
internal class BottomStackState(
    val hasContentState: MutableState<Boolean>,
    val decorationsHeightPercentState: State<Float>,
)

@Composable
internal fun rememberBottomStackState(): BottomStackState {
    val hasContentState = remember { mutableStateOf(true) }
    val decorationsHeightPercentState = animateFloatAsState(if (hasContentState.value) 1f else 0f)

    return remember {
        BottomStackState(
            hasContentState = hasContentState,
            decorationsHeightPercentState = decorationsHeightPercentState,
        )
    }
}
