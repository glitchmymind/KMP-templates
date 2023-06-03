import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import theme.UiKitTheme
import typography.HeaderM

@Composable
fun LoadableImage(
    url: String,
    modifier: Modifier = Modifier,
    placeholder: Painter? = null,
    contentScale: ContentScale = ContentScale.Fit,
    alignment: Alignment = Alignment.Center,
    onError: () -> Unit = {},
    onSuccess: () -> Unit = {},
    tint: Color? = null,
) {
    var shimmerState by remember { mutableStateOf(false) }
//    AsyncImage(
//        model = url,
//        modifier =
//            modifier.shimmer(visible = shimmerState).testTag(LoadableImageElement).semantics {
//                imageUrl = url
//            },
//        contentScale = contentScale,
//        alignment = alignment,
//        onError = { onError.invoke() },
//        onLoading = { shimmerState = true },
//        onSuccess = {
//            onSuccess.invoke()
//            shimmerState = false
//        },
//        placeholder = placeholder,
//        colorFilter = tint?.let { ColorFilter.tint(color = it) },
//        contentDescription = null,
//    )
}

@Composable
fun LoadableImageOrInitials(
    url: String?,
    firstName: String,
    lastName: String?,
    modifier: Modifier = Modifier,
    initialsStyle: TextStyle = HeaderM,
    contentScale: ContentScale = ContentScale.Fit,
) {
    val initialsVisibilityState = remember { mutableStateOf(false) }
    Box {
        if (!initialsVisibilityState.value && url != null) {
            LoadableImage(
                modifier = modifier,
                url = url,
                contentScale = contentScale,
                onError = { initialsVisibilityState.value = true },
            )
        } else {
            Box(
                modifier = modifier.background(UiKitTheme.colors.c7_t),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = formatInitials(firstName, lastName),
                    style = initialsStyle,
                    color = UiKitTheme.colors.c7,
                )
            }
        }
    }
}

private fun formatInitials(
    firstName: String,
    lastName: String? = null,
): String {
    val parts =
        if (lastName == null) {
            firstName.split(" ")
        } else {
            listOf(firstName, lastName)
        }

    return if (parts.size > 1) {
            parts[0].take(1) + parts[1].take(1)
        } else {
            parts[0].take(2)
        }
        .uppercase()
}
