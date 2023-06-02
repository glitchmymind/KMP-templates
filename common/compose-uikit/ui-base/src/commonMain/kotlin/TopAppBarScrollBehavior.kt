import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource

@Stable
interface TopAppBarScrollBehavior {

    /**
     * A [NestedScrollConnection] that should be attached to a [Modifier.nestedScroll] in order to
     * keep track of the scroll events.
     */
    val nestedScrollConnection: NestedScrollConnection

    /**
     * Returns the top app bar's current scroll fraction.
     *
     * A scrollFraction is a value between `0.0` to `1.0` that provides a percentage of the app bar
     * scroll position when the content is scrolled. `0.0` represents an expanded app bar, while
     * `1.0` represents a collapsed one (e.g. the app bar is scrolled to its target offset). Note
     * that this value will be updated on scroll even if the [offset] is pinned to a specific value
     * (see [TopAppBarDefaults.pinnedScrollBehavior]). In this case a value of 1.0 represents that
     * the scroll value has exceeded the height of the pinned app bar, as if the app bar was
     * collapsing.
     */
    val scrollFraction: Float

    /**
     * The top app bar's offset limit in pixels, which represents the offset that a top app bar is
     * allowed to scroll when the scrollable content is scrolled.
     *
     * This limit is represented by a negative [Float], and used to coerce the [offset] value when
     * the content is scrolled.
     */
    var offsetLimit: Float

    /**
     * The top app bar's current offset in pixels.
     *
     * The offset is usually between zero and the [offsetLimit].
     */
    var offset: Float

    /**
     * The current content offset that is updated when the nested scroll connection consumes scroll
     * events.
     *
     * A common behavior implementation would update this value to be the sum of all
     * [NestedScrollConnection.onPostScroll] `consumed.y` values.
     */
    var contentOffset: Float
}

class PinnedScrollBehavior(
    override var offsetLimit: Float,
    val canScroll: () -> Boolean = { true },
) : TopAppBarScrollBehavior {
    override val scrollFraction: Float
        get() =
            if (offsetLimit != 0f) {
                1 -
                    (offsetLimit - contentOffset).coerceIn(
                        minimumValue = offsetLimit,
                        maximumValue = 0f,
                    ) / offsetLimit
            } else {
                0f
            }
    override var offset = 0f
    override var contentOffset by mutableStateOf(0f)
    override var nestedScrollConnection =
        object : NestedScrollConnection {
            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource,
            ): Offset {
                if (!canScroll()) return Offset.Zero
                if (consumed.y == 0f && available.y > 0f) {
                    // Reset the total offset to zero when scrolling all the way down. This will
                    // eliminate some float precision inaccuracies.
                    contentOffset = 0f
                } else {
                    contentOffset += consumed.y
                }
                return Offset.Zero
            }
        }

    companion object {

        @Suppress("FunctionNaming")
        fun Saver(offsetLimit: Float): Saver<PinnedScrollBehavior, Float> =
            Saver(
                save = { it.contentOffset },
                restore = { PinnedScrollBehavior(offsetLimit).apply { contentOffset = it } },
            )
    }
}
