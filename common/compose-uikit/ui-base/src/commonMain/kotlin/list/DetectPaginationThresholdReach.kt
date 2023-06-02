import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.filterNotNull

@Composable
fun DetectPaginationThresholdReach(
    lazyListState: LazyListState,
    threshold: Int = 3,
    onDetected: () -> Unit,
) {
    val actualOnDetected by rememberUpdatedState(onDetected)
    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .filterNotNull()
            .filter { index -> index >= lazyListState.layoutInfo.totalItemsCount - threshold }
            .collect { actualOnDetected.invoke() }
    }
}
