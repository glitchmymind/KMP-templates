import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import theme.UiKitTheme
import typography.LabelLarge
import typography.LabelSmall

object TextFieldTokens {

    val shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)

    val shapeWithoutIndicator = RoundedCornerShape(8.dp)

    val placeholderStyle
        @Composable @ReadOnlyComposable get() = LabelLarge.copy(color = UiKitTheme.colors.c2)

    val textStyle
        @Composable @ReadOnlyComposable get() = LabelLarge.copy(color = UiKitTheme.colors.c1)

    val descriptionTextStyle
        @Composable @ReadOnlyComposable get() = LabelSmall.copy(color = UiKitTheme.colors.c2)

    @Composable
    fun textFieldColors(
        textColor: Color = UiKitTheme.colors.c1,
        placeholderColor: Color = UiKitTheme.colors.c3,
        backgroundColor: Color = UiKitTheme.colors.c5,
        focusedLabelColor: Color = UiKitTheme.colors.c6,
        unfocusedLabelColor: Color = UiKitTheme.colors.c2,
        focusedIndicatorColor: Color = UiKitTheme.colors.c6,
        unfocusedIndicatorColor: Color = Color.Transparent,
        errorCursorColor: Color = UiKitTheme.colors.c8,
        errorLabelColor: Color = UiKitTheme.colors.c8,
        errorTrailingIconColor: Color = UiKitTheme.colors.c8,
        errorIndicatorColor: Color = UiKitTheme.colors.c8,
        errorLeadingIconColor: Color = UiKitTheme.colors.c8,
    ) =
        TextFieldDefaults.textFieldColors(
            textColor = textColor,
            placeholderColor = placeholderColor,
            backgroundColor = backgroundColor,
            focusedLabelColor = focusedLabelColor,
            unfocusedLabelColor = unfocusedLabelColor,
            focusedIndicatorColor = focusedIndicatorColor,
            unfocusedIndicatorColor = unfocusedIndicatorColor,
            errorCursorColor = errorCursorColor,
            errorLabelColor = errorLabelColor,
            errorTrailingIconColor = errorTrailingIconColor,
            errorIndicatorColor = errorIndicatorColor,
            errorLeadingIconColor = errorLeadingIconColor,
        )
}
