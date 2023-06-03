import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
class LoadingIndicatorSize private constructor(val dotSize: Dp, val spaceBetweenDots: Dp) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is LoadingIndicatorSize) return false

        if (dotSize != other.dotSize) return false
        if (spaceBetweenDots != other.spaceBetweenDots) return false

        return true
    }

    override fun hashCode(): Int {
        var result = dotSize.hashCode()
        result = 31 * result + spaceBetweenDots.hashCode()
        return result
    }

    override fun toString(): String {
        return "LoadingIndicatorSize(" +
            "dotSize=$dotSize, " +
            "spaceBetweenDots=$spaceBetweenDots" +
            ")"
    }

    companion object {

        @Stable val Medium = LoadingIndicatorSize(dotSize = 8.dp, spaceBetweenDots = 10.dp)

        @Stable val Small = LoadingIndicatorSize(dotSize = 6.dp, spaceBetweenDots = 8.dp)

        @Stable val XSmall = LoadingIndicatorSize(dotSize = 5.dp, spaceBetweenDots = 4.dp)
    }
}
