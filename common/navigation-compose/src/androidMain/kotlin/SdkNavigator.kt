import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import api.generateGraph
import color.LocalBackgroundColor
import com.adeo.kviewmodel.odyssey.setupWithViewModels
import ru.alexgladkov.odyssey.compose.base.Navigator
import ru.alexgladkov.odyssey.compose.extensions.setupWithActivity
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalNavigator
import theme.UiKitTheme

actual class SdkNavigator(private val activity: ComponentActivity) {
    actual fun initNavigation() {
        val rootController = RootComposeBuilder().apply {
            generateGraph(NavigationSource.Android)
            //    screen(name = NavigationTree.Splash.SplashScreen.name) {
//        SplashScreen()
//    }
//            authFlow()
//            mainFlow()
        }.build()
        rootController.setupWithActivity(activity)
        rootController.setupWithViewModels()

        activity.setContent {
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
}