//@file:Suppress("detekt.MatchingDeclarationName")
//
//import androidx.compose.animation.core.animateDpAsState
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.material.Icon
//import androidx.compose.material.IconButton
//import androidx.compose.material.Text
//import androidx.compose.material.icons.Icons
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.remember
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.painter.Painter
//import androidx.compose.ui.platform.testTag
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import theme.UiKitTheme
//import typography.HeaderM
//import typography.LabelLarge
//import typography.LabelSmall
//import vivid.money.ui_compose_base.ActionsScope
//
//@Composable
//fun TopAppBar(
//    modifier: Modifier = Modifier,
//    title: @Composable TopAppBarTitleScope.() -> Unit = {},
//    actions: @Composable ActionsScope.() -> Unit = {},
//    navIcon: Painter? = painterResource(id = Icons.ic_24_arrow_back_android),
//    navIconTint: Color = UiKitTheme.colors.c6,
//    scrollBehavior: TopAppBarScrollBehavior? = null,
//    backgroundColor: Color = LocalBackgroundColor.current,
//    contentPadding: PaddingValues =
//        rememberInsetsPaddingValues(LocalWindowInsets.current.statusBars),
//    onNavClick: () -> Unit = {},
//) {
//    val animatedElevation by
//        animateDpAsState(
//            targetValue = if (scrollBehavior?.scrollFraction ?: 0f >= 1) 8.dp else 0.dp,
//        )
//    com.google.accompanist.insets.ui.TopAppBar(
//        title = { title.invoke(TopAppBarTitleScope) },
//        backgroundColor = backgroundColor,
//        contentColor = UiKitTheme.colors.c1,
//        navigationIcon =
//            navIcon?.let { icon ->
//                @Composable
//                {
//                    IconButton(
//                        onClick = onNavClick,
//                        modifier = Modifier.testTag(UiComposeBaseTestTags.TopBarNavIconElement),
//                    ) {
//                        Icon(
//                            painter = icon,
//                            contentDescription = null,
//                            tint = navIconTint,
//                        )
//                    }
//                }
//            },
//        actions = {
//            val actionsScope = remember(this) { ActionsScope(rowScope = this) }
//            actions.invoke(actionsScope)
//        },
//        contentPadding = contentPadding,
//        elevation = animatedElevation,
//        modifier = modifier,
//    )
//}
//
//object TopAppBarTitleScope {
//
//    @Composable
//    fun TopAppBarTitle(text: String, modifier: Modifier = Modifier) {
//        Text(
//            text = text,
//            style = HeaderM,
//            color = UiKitTheme.colors.c1,
//            modifier = modifier,
//        )
//    }
//
//    @Composable
//    fun TopAppBarTwoLineTitle(title: String, caption: String, modifier: Modifier = Modifier) {
//        Column(modifier) {
//            Text(text = title, style = LabelLarge, color = UiKitTheme.colors.c1)
//            Text(text = caption, style = LabelSmall, color = UiKitTheme.colors.c2)
//        }
//    }
//}
