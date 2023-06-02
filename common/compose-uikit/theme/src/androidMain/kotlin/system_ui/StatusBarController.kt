package system_ui

import android.graphics.drawable.ColorDrawable
import android.view.View
import androidx.annotation.ColorInt
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember

class StatusBarController(
    private val view: View,
) {
    @ColorInt
    private val initialColor: Int = (view.background as ColorDrawable).color

    fun setColor(color: Int) {
        view.setBackgroundColor(color)
    }

    fun restoreColor() {
        view.setBackgroundColor(initialColor)
    }
}

@Composable
fun rememberStatusBarController(statusBar: View): StatusBarController {
    return remember(statusBar) { StatusBarController(statusBar) }
}

val LocalStatusBarController =
    compositionLocalOf<StatusBarController> {
        error("CompositionLocal LocalStatusBarController not present")
    }
