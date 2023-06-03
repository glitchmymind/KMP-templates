@file:Suppress("LongParameterList")

import UiComposeTextFieldsTestTags.TextFieldElement
import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import theme.UiKitTheme

@Composable
fun RegularTextField(
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    text: String,
    description: String? = null,
    isError: Boolean = false,
    onErrorIconClick: (() -> Unit)? = null,
    placeholderText: String? = null,
    labelText: String? = null,
    maxLines: Int = Int.MAX_VALUE,
    enabled: Boolean = true,
    singleLine: Boolean = false,
    clearable: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    colors: TextFieldColors = TextFieldTokens.textFieldColors(),
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    onFocusChanged: (FocusState) -> Unit = {},
    descriptionContent: @Composable () -> Unit = {
        if (description?.isNotBlank() == true) {
            TextFieldDescription(description = description, isError = isError)
        }
    },
    onValueChange: (String) -> Unit,
) {
    var textFieldValueState by remember { mutableStateOf(TextFieldValue(text = text)) }
    val textFieldValue = textFieldValueState.copy(text = text)
    RegularTextField(
        modifier = modifier,
        textFieldModifier = textFieldModifier,
        text = textFieldValue,
        description = description,
        isError = isError,
        onErrorIconClick = onErrorIconClick,
        placeholderText = placeholderText,
        labelText = labelText,
        maxLines = maxLines,
        enabled = enabled,
        singleLine = singleLine,
        clearable = clearable,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        trailingIcon = trailingIcon,
        leadingIcon = leadingIcon,
        colors = colors,
        onFocusChanged = onFocusChanged,
        descriptionContent = descriptionContent,
        visualTransformation = visualTransformation,
        onValueChange = { newTextFieldValue ->
            textFieldValueState = newTextFieldValue
            val newText = newTextFieldValue.text
            if (text != newText) {
                onValueChange.invoke(newText)
            }
        },
    )
}

@Composable
fun RegularTextField(
    modifier: Modifier = Modifier,
    textFieldModifier: Modifier = Modifier,
    text: TextFieldValue,
    description: String? = null,
    isError: Boolean = false,
    onErrorIconClick: (() -> Unit)? = null,
    placeholderText: String? = null,
    labelText: String? = null,
    maxLines: Int = Int.MAX_VALUE,
    enabled: Boolean = true,
    singleLine: Boolean = false,
    clearable: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions(),
    visualTransformation: VisualTransformation = VisualTransformation.None,
    colors: TextFieldColors = TextFieldTokens.textFieldColors(),
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    onFocusChanged: (FocusState) -> Unit = {},
    descriptionContent: @Composable () -> Unit = {
        if (description?.isNotBlank() == true) {
            TextFieldDescription(description = description, isError = isError)
        }
    },
    onValueChange: (TextFieldValue) -> Unit,
) {
    var shape by remember { mutableStateOf(TextFieldTokens.shapeWithoutIndicator) }
    var isFocused by remember { mutableStateOf(false) }
    Column(modifier) {
        TextField(
            value = text,
            onValueChange = onValueChange,
            textStyle =
            if (isError && !isFocused) {
                TextFieldTokens.textStyle.copy(color = UiKitTheme.colors.c8)
            } else {
                TextFieldTokens.textStyle
            },
            placeholder =
            placeholderText?.let {
                { Text(text = placeholderText, style = TextFieldTokens.placeholderStyle) }
            },
            colors = colors,
            label = labelText?.let { { Text(text = labelText) } },
            modifier =
            textFieldModifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    shape =
                        if (focusState.isFocused) {
                            TextFieldTokens.shape
                        } else {
                            TextFieldTokens.shapeWithoutIndicator
                        }
                    isFocused = focusState.isFocused
                    onFocusChanged.invoke(focusState)
                }
                .testTag(TextFieldElement),
            isError = isError,
            shape = shape,
            maxLines = maxLines,
            singleLine = singleLine,
            trailingIcon = {
                var errorInfoIconVisible = false
                var clearIconVisible = false
                var trailingIconVisible = false

                when {
                    isError && onErrorIconClick != null -> errorInfoIconVisible = true
                    clearable && text.text.isNotBlank() && isFocused -> clearIconVisible = true
                    trailingIcon != null -> trailingIconVisible = true
                }

                AnimatedIcon(
                    icon = Icons.Default.Info,
                    tint = UiKitTheme.colors.c8,
                    visible = errorInfoIconVisible,
                ) { onErrorIconClick?.invoke() }
                AnimatedIcon(
                    icon = Icons.Default.Clear,
                    tint = UiKitTheme.colors.c3,
                    visible = clearIconVisible,
                ) { onValueChange(TextFieldValue("")) }
                AnimatedIcon(
                    visible = trailingIconVisible,
                ) { trailingIcon?.invoke() }
            },
            leadingIcon = leadingIcon,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            visualTransformation = visualTransformation,
            enabled = enabled,
        )
        descriptionContent()
    }
}

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    text: String,
    description: String? = null,
    isError: Boolean = false,
    placeholderText: String? = null,
    keyboardActionDone: () -> Unit,
    onFocusChanged: (FocusState) -> Unit,
    onValueChange: (String) -> Unit,
) {
    val passwordHiddenState = rememberSaveable { mutableStateOf(true) }
    val focusManager = LocalFocusManager.current
    var isFocused by remember { mutableStateOf(false) }
    Column(modifier) {
        TextField(
            value = text,
            onValueChange = onValueChange,
            textStyle =
            if (isError && !isFocused) {
                TextFieldTokens.textStyle.copy(color = UiKitTheme.colors.c8)
            } else {
                TextFieldTokens.textStyle
            },
            placeholder =
            placeholderText?.let {
                { Text(text = placeholderText, style = TextFieldTokens.placeholderStyle) }
            },
            colors = TextFieldTokens.textFieldColors(),
            modifier =
            Modifier.fillMaxWidth().onFocusChanged { focusState ->
                isFocused = focusState.isFocused
                onFocusChanged.invoke(focusState)
            },
            isError = isError,
            shape = TextFieldTokens.shape,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions =
            KeyboardActions(
                onDone = {
                    keyboardActionDone.invoke()
                    focusManager.clearFocus()
                },
            ),
            visualTransformation =
            if (passwordHiddenState.value) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = { passwordHiddenState.value = !passwordHiddenState.value }) {
                    Icon(
                        imageVector = if (passwordHiddenState.value) {
                            Icons.Default.Lock
                        } else {
                            Icons.Default.Lock
                        },
                        contentDescription = null,
                        tint = UiKitTheme.colors.c3,
                    )
                }
            },
        )
        if (description?.isNotBlank() == true) {
            TextFieldDescription(description = description, isError = isError)
        }
    }
}

@Composable
private fun AnimatedIcon(
    icon: Painter,
    tint: Color,
    visible: Boolean,
    onClick: () -> Unit,
) {
    AnimatedIcon(visible = visible) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.clickable(onClick = onClick),
        )
    }
}

@Composable
private fun AnimatedIcon(
    icon: ImageVector,
    tint: Color,
    visible: Boolean,
    onClick: () -> Unit,
) {
    AnimatedIcon(visible = visible) {
        Icon(
            modifier = Modifier.clickable { Modifier.clickable(onClick = onClick) },
            imageVector = icon,
            contentDescription = null,
            tint = tint,
        )
    }
}

@Composable
private fun AnimatedIcon(
    visible: Boolean,
    content: @Composable () -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter =
        expandIn(
            expandFrom = Alignment.Center,
            animationSpec = tween(durationMillis = 150),
        ),
        exit =
        shrinkOut(
            shrinkTowards = Alignment.Center,
            animationSpec = tween(durationMillis = 150),
        ),
        content = {
            CompositionLocalProvider(
                LocalIndication provides rememberRipple(bounded = false),
            ) { content.invoke() }
        },
    )
}

@Composable
private fun TextFieldDescription(
    description: String,
    isError: Boolean,
) {
    val textStyle =
        if (isError) {
            TextFieldTokens.descriptionTextStyle.copy(color = UiKitTheme.colors.c8)
        } else {
            TextFieldTokens.descriptionTextStyle
        }
    val padding =
        if (description.isBlank()) {
            Modifier
        } else {
            Modifier.padding(start = 16.dp, top = 5.dp)
        }
    AnimatedVisibility(
        visible = description.isNotBlank(),
        enter = expandVertically(),
        exit = shrinkVertically(),
    ) {
        Text(
            text = description,
            style = textStyle,
            modifier = padding,
        )
    }
}
