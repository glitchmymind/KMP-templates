import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import color.LocalBackgroundColor
import preview.PreviewBox

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Card(
    style: CardStyle,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(LocalBackgroundColor provides style.backgroundColor) {
        androidx.compose.material.Card(
            modifier = modifier.fillMaxWidth(),
            shape = CardTokens.Shape,
            elevation = style.elevation,
            backgroundColor = LocalBackgroundColor.current,
            onClick = { onClick?.invoke() },
            enabled = enabled && onClick != null,
            content = content,
        )
    }
}

//@Preview
//@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun CommonCardPreview() {
    PreviewBox {
        Column {
            Card(
                style = CardStyle.Plain,
                modifier = Modifier.padding(horizontal = CardTokens.HorizontalPadding),
                onClick = {},
            ) { Column(Modifier.padding(16.dp)) { Text(text = "Clickable") } }
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                style = CardStyle.Plain,
                enabled = false,
                modifier = Modifier.padding(horizontal = CardTokens.HorizontalPadding),
            ) { Column(Modifier.padding(16.dp)) { Text(text = "Not clickable") } }
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                style = CardStyle.Elevated,
                modifier = Modifier.padding(horizontal = CardTokens.HorizontalPadding),
                onClick = {},
            ) { Column(Modifier.padding(16.dp)) { Text(text = "Clickable") } }
            Spacer(modifier = Modifier.height(32.dp))
            Card(
                style = CardStyle.Elevated,
                enabled = false,
                modifier = Modifier.padding(horizontal = CardTokens.HorizontalPadding),
            ) { Column(Modifier.padding(16.dp)) { Text(text = "Not clickable") } }
        }
    }
}
