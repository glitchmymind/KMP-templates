package api

import NavigationSource
import NavigationTree
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import color.LocalBackgroundColor
import com.adeo.kviewmodel.odyssey.setupWithViewModels
import ru.alexgladkov.odyssey.compose.base.Navigator
import ru.alexgladkov.odyssey.compose.extensions.setupWithActivity
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalNavigator
import theme.UiKitTheme

fun ComponentActivity.setupThemedNavigation() {
    val rootController = RootComposeBuilder().apply { generateGraph(NavigationSource.Android) }.build()
    rootController.setupWithActivity(this)
    rootController.setupWithViewModels()

    setContent {
        UiKitTheme {
            val backgroundColor = LocalBackgroundColor.current
            rootController.backgroundColor = backgroundColor

            CompositionLocalProvider(
                LocalRootController provides rootController
            ) {
                ModalNavigator {
                    Navigator(startScreen = NavigationTree.Splash.SplashScreen.name)
                }
            }
        }
    }
}