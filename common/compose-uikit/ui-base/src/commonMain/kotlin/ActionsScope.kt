//import UiComposeBaseTestTags.TextActionElement
//import androidx.compose.animation.animateColorAsState
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.RowScope
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Icon
//import androidx.compose.material.IconButton
//import androidx.compose.material.Text
//import androidx.compose.material.ripple.rememberRipple
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.platform.testTag
//import androidx.compose.ui.unit.dp
//import theme.UiKitTheme
//import typography.LabelLarge
//import vivid.money.common_compose_theme.theme.VividTheme
//import vivid.money.common_compose_theme.typography.LabelLarge
//import vivid.money.ui_compose_base.UiComposeBaseTestTags.TextActionElement
//import vivid.money.ui_compose_labels.LabelBackground
//import vivid.money.ui_compose_labels.LabelIcon
//import vivid.money.ui_compose_labels.LabelSize
//import vivid.money.ui_compose_labels.text_content.TextContentLabel
//
//class ActionsScope(rowScope: RowScope) : RowScope by rowScope {
//
//    @Composable
//    fun TextAction(
//        text: String,
//        modifier: Modifier = Modifier,
//        enabled: Boolean = true,
//        onClick: () -> Unit,
//    ) {
//        vivid.money.ui_compose_base.TextAction(
//            text = text,
//            modifier = modifier,
//            enabled = enabled,
//            onClick = onClick,
//        )
//    }
//
//    @Composable
//    fun IconAction(
//        painter: Painter,
//        modifier: Modifier = Modifier,
//        color: Color = UiKitTheme.colors.c6,
//        enabled: Boolean = true,
//        onClick: () -> Unit,
//    ) {
//        vivid.money.ui_compose_base.IconAction(
//            painter = painter,
//            modifier = modifier,
//            color = color,
//            enabled = enabled,
//            onClick = onClick,
//        )
//    }
//
//    @Composable
//    fun TextLabel(
//        icon: LabelIcon?,
//        labelSize: LabelSize,
//        text: String,
//        textColor: Color,
//        padding: PaddingValues,
//        background: LabelBackground,
//        modifier: Modifier = Modifier,
//    ) {
//        TextContentLabel(
//            icon = icon,
//            labelSize = labelSize,
//            text = text,
//            textColor = textColor,
//            padding = padding,
//            background = background,
//            modifier = modifier,
//        )
//    }
//}
//
//@Composable
//fun TextAction(
//    text: String,
//    modifier: Modifier = Modifier,
//    enabled: Boolean = true,
//    onClick: () -> Unit,
//) {
//    val interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
//    val contentColor: Color by
//        animateColorAsState(
//            if (enabled) UiKitTheme.colors.c6 else UiKitTheme.colors.c6.copy(alpha = 0.5f),
//        )
//    Text(
//        text = text,
//        style = LabelLarge,
//        color = contentColor,
//        modifier =
//            modifier
//                .clip(RoundedCornerShape(4.dp))
//                .clickable(
//                    interactionSource = interactionSource,
//                    indication = rememberRipple(),
//                    enabled = enabled,
//                    onClick = onClick,
//                )
//                .padding(
//                    vertical = 10.dp,
//                    horizontal = 16.dp,
//                )
//                .testTag(TextActionElement),
//    )
//}
//
//@Composable
//fun IconAction(
//    painter: Painter,
//    modifier: Modifier = Modifier,
//    color: Color = UiKitTheme.colors.c6,
//    enabled: Boolean = true,
//    onClick: () -> Unit,
//) {
//    val contentColor: Color by animateColorAsState(if (enabled) color else color.copy(alpha = 0.5f))
//
//    IconButton(
//        modifier = modifier,
//        onClick = onClick,
//        enabled = enabled,
//    ) {
//        Icon(
//            painter = painter,
//            contentDescription = null,
//            tint = contentColor,
//        )
//    }
//}
