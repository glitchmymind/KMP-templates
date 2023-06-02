//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Row
//import androidx.compose.material.Icon
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.lerp
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.Dp
//import androidx.compose.ui.unit.dp
//import com.google.accompanist.pager.ExperimentalPagerApi
//import com.google.accompanist.pager.PagerState
//import theme.UiKitTheme
//import kotlin.math.absoluteValue
//import vivid.money.common_compose_theme.theme.VividTheme
//import vivid.money.res_icons.Icons
//
////@OptIn(ExperimentalPagerApi::class)
//@Composable
//fun PagerIndicator(
//    pagerState: PagerState,
//    modifier: Modifier = Modifier,
//    activeColor: Color = UiKitTheme.colors.c6,
//    inactiveColor: Color = UiKitTheme.colors.c4,
//    spacing: Dp = 0.dp,
//    indicatorProvider: (Int) -> Int? = { null },
//) {
//    Row(
//        modifier = modifier,
//        horizontalArrangement = Arrangement.spacedBy(spacing),
//        verticalAlignment = Alignment.CenterVertically,
//    ) {
//        val fraction = pagerState.currentPageOffset.absoluteValue.coerceIn(0f, 1f)
//        val currentColor = lerp(activeColor, inactiveColor, fraction)
//        val nextColor = lerp(inactiveColor, activeColor, fraction)
//
//        repeat(pagerState.pageCount) {
//            val painter = painterResource(id = indicatorProvider.invoke(it) ?: Icons.ic_15_dot)
//            Icon(
//                painter = painter,
//                contentDescription = null,
//                tint =
//                    when {
//                        it == pagerState.currentPage -> currentColor
//                        pagerState.currentPageOffset > 0 && it == pagerState.currentPage + 1 ||
//                            pagerState.currentPageOffset < 0 && it == pagerState.currentPage - 1 ->
//                            nextColor
//                        else -> inactiveColor
//                    },
//            )
//        }
//    }
//}
