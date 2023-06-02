//package vivid.money.ui_compose_base
//
//import android.graphics.Typeface
//import android.text.style.*
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.toArgb
//import androidx.compose.ui.text.AnnotatedString
//import androidx.compose.ui.text.SpanStyle
//import androidx.compose.ui.text.buildAnnotatedString
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextDecoration
//import androidx.core.text.getSpans
//import androidx.core.text.parseAsHtml
//import color.UiKitColors
//import color.findColor
//import vivid.money.common_compose_theme.color.VividColors
//import vivid.money.common_compose_theme.color.findColor
//
//private val COLOR_STYLE_REGEX = Regex("(color:\\s?)([cC]\\d+)")
//
//@Suppress("LongMethod")
//fun String.parseAsHtml(
//    colors: UiKitColors,
//    annotationTagProvider: ((colorSpanIndex: Int) -> String?)? = null,
//): AnnotatedString {
//    val spannedText = replaceWithHexColor(colors).parseAsHtml()
//    return buildAnnotatedString {
//        append(spannedText.toString())
//        spannedText.getSpans<CharacterStyle>().forEachIndexed { index, span ->
//            val start = spannedText.getSpanStart(span)
//            val end = spannedText.getSpanEnd(span)
//
//            annotationTagProvider?.invoke(index)?.let { tag ->
//                addStringAnnotation(
//                    tag = tag,
//                    annotation = (span as? URLSpan)?.url ?: "",
//                    start = start,
//                    end = end,
//                )
//            }
//
//            when {
//                span is ForegroundColorSpan -> {
//                    addStyle(
//                        style = SpanStyle(color = Color(span.foregroundColor)),
//                        start = start,
//                        end = end,
//                    )
//                }
//                span is StyleSpan ->
//                    when (span.style) {
//                        Typeface.NORMAL ->
//                            addStyle(
//                                SpanStyle(
//                                    fontWeight = FontWeight.Normal,
//                                    fontStyle = FontStyle.Normal,
//                                ),
//                                start,
//                                end,
//                            )
//                        Typeface.BOLD ->
//                            addStyle(
//                                SpanStyle(
//                                    fontWeight = FontWeight.Bold,
//                                    fontStyle = FontStyle.Normal,
//                                ),
//                                start,
//                                end,
//                            )
//                        Typeface.ITALIC ->
//                            addStyle(
//                                SpanStyle(
//                                    fontWeight = FontWeight.Normal,
//                                    fontStyle = FontStyle.Italic,
//                                ),
//                                start,
//                                end,
//                            )
//                        Typeface.BOLD_ITALIC ->
//                            addStyle(
//                                SpanStyle(
//                                    fontWeight = FontWeight.Bold,
//                                    fontStyle = FontStyle.Italic,
//                                ),
//                                start,
//                                end,
//                            )
//                    }
//                span is URLSpan -> {
//                    addStyle(
//                        style = SpanStyle(color = colors.c6),
//                        start = start,
//                        end = end,
//                    )
//                }
//                span is StrikethroughSpan -> {
//                    addStyle(
//                        style = SpanStyle(textDecoration = TextDecoration.LineThrough),
//                        start = start,
//                        end = end,
//                    )
//                }
//            }
//        }
//    }
//}
//
//private fun String.replaceWithHexColor(colors: UiKitColors): String {
//    return replace(COLOR_STYLE_REGEX) { matchResult ->
//        val (text, color) = matchResult.destructured
//        // get HEX value of the color without alpha component. (for correct HTML parsing into Span)
//        val hex = Integer.toHexString(colors.findColor(key = color).toArgb()).drop(n = 2)
//        val colorHex = "#$hex"
//
//        "$text$colorHex"
//    }
//}
