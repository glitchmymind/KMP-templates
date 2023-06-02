package preview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import color.LocalBackgroundColor
import theme.UiKitTheme

@Composable
fun PreviewBox(modifier: Modifier = Modifier, content: @Composable () -> Unit) {
    UiKitTheme { Box(modifier.background(color = LocalBackgroundColor.current)) { content() } }
}
