//import android.content.res.Configuration
//import androidx.compose.desktop.ui.tooling.preview.Preview
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.text.style.TextOverflow
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import preview.PreviewBox
//import theme.UiKitTheme
//import typography.HeaderM
//import typography.LabelNormal
//import vivid.money.common_compose_theme.preview.PreviewBox
//import vivid.money.common_compose_theme.theme.VividTheme
//import vivid.money.common_compose_theme.typography.HeaderM
//import vivid.money.common_compose_theme.typography.LabelNormal
//import vivid.money.ui_compose_base.ActionsScope
//import vivid.money.ui_compose_shimmers.shimmer
//import java.lang.module.Configuration
//
//@Composable
//fun SectionTitle(
//    title: String,
//    modifier: Modifier = Modifier,
//    titleMaxLines: Int = 1,
//    subtitle: String? = null,
//    subtitleMaxLines: Int = 1,
//    subtitleSoftWrap: Boolean = false,
//    action: (@Composable ActionsScope.() -> Unit)? = null,
//    shimmerVisible: Boolean = false,
//) {
//    Row(
//        modifier = modifier.fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        Column(
//            modifier =
//                Modifier.padding(vertical = 12.dp, horizontal = 16.dp).weight(1f).fillMaxWidth(),
//        ) {
//            Text(
//                text = title,
//                style = HeaderM,
//                maxLines = titleMaxLines,
//                softWrap = false,
//                overflow = TextOverflow.Ellipsis,
//                color = UiKitTheme.colors.c1,
//                modifier =
//                    Modifier.shimmer(
//                        visible = shimmerVisible,
//                        shape = RoundedCornerShape(8.dp),
//                    ),
//            )
//            subtitle?.let { subtitle ->
//                Spacer(modifier = Modifier.height(2.dp))
//                Text(
//                    text = subtitle,
//                    style = LabelNormal,
//                    maxLines = subtitleMaxLines,
//                    softWrap = subtitleSoftWrap,
//                    overflow = TextOverflow.Ellipsis,
//                    color = UiKitTheme.colors.c2,
//                )
//            }
//        }
//        action?.let { action ->
//            val actionsScope = remember(this) { ActionsScope(rowScope = this) }
//            action.invoke(actionsScope)
//        }
//    }
//}
//
//@Preview
////@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
//@Composable
//fun SectionTitleWithTextActionPreview() {
//    PreviewBox {
//        SectionTitle(
//            title = "Title",
//            action = {
//                TextAction(text = "Action") {
//                    // click
//                }
//            },
//        )
//    }
//}
