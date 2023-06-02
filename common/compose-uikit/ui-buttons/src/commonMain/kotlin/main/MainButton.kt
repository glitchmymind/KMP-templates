@file:Suppress("MatchingDeclarationName")

import UiComposeButtonsTestTags.MainButtonElement
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource

@Composable
fun MainButton(
    style: MainButtonStyle,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    elevation: ButtonElevation? = null,
    enabled: Boolean = true,
    progress: Boolean = false,
    content: @Composable MainButtonContentScope.() -> Unit,
) {
    Button(
        modifier =
            modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = MainButtonTokens.MinHeight)
                .testTag(MainButtonElement),
        shape = MainButtonTokens.Shape,
        colors = style.buttonColors(),
        elevation = elevation,
        enabled = enabled,
        onClick = { if (!progress) onClick.invoke() },
    ) {
        CommonProgressButtonContent(
            progress = progress,
            indicatorSize = LoadingIndicatorSize.Medium,
        ) { content.invoke(MainButtonContentScope) }
    }
}

object MainButtonContentScope {

    @Composable
    fun Text(text: String) {
        Text(text = text, style = MainButtonTokens.TextStyle)
    }

    @Composable
    fun TextWithStartIcon(
        text: String,
        icon: MainButtonIcon,
    ) {
        ButtonIcon(icon = icon)
        Spacer(modifier = Modifier.width(MainButtonTokens.IconSpacing))
        Text(text = text)
    }

    @Composable
    fun TextWithEndIcon(
        text: String,
        icon: MainButtonIcon,
    ) {
        Text(text = text)
        Spacer(modifier = Modifier.width(MainButtonTokens.IconSpacing))
        ButtonIcon(icon = icon)
    }

    @Composable
    fun ButtonIcon(
        icon: MainButtonIcon,
    ) =
        when (icon) {
            is MainButtonIcon.Loadable ->
                LoadableImage(modifier = Modifier.size(MainButtonTokens.IconSize), url = icon.url)
            is MainButtonIcon.Icon -> {
                Icon(
                    modifier = Modifier.size(MainButtonTokens.IconSize),
                    painter = imageResources(icon.name), // TODO: painterResource(id = icon.id)
                    contentDescription = null,
                )
            }
            is MainButtonIcon.Image ->
                Image(
                    modifier = Modifier.size(MainButtonTokens.IconSize),
                    painter = imageResources(icon.name), // TODO: painterResource(id = icon.id)
                    contentDescription = null,
                )

            else -> {}
        }
}
