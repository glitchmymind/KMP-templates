import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.awt.ComposePanel
import api.generateGraph
import color.LocalBackgroundColor
import ru.alexgladkov.odyssey.compose.base.Navigator
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalNavigator
import theme.UiKitTheme
import java.awt.BorderLayout
import javax.swing.JFrame
import javax.swing.WindowConstants

actual class SdkNavigator(private val jFrame: JFrame) {
    actual fun initNavigation() {
        val rootController = RootComposeBuilder().apply {
            generateGraph(NavigationSource.Desktop)
        }.build()

        jFrame.defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
        jFrame.title = "Multiplatform template"

        val composePanel = ComposePanel()
        composePanel.setContent {
            CompositionLocalProvider(
                LocalRootController provides rootController
            ) {
                UiKitTheme {
                    val backgroundColor = LocalBackgroundColor.current
                    rootController.backgroundColor = backgroundColor

                    ModalNavigator {
                        Navigator(startScreen = NavigationTree.Splash.SplashScreen.name)
                    }
                }
            }
        }

        with(jFrame) {
            contentPane.add(composePanel, BorderLayout.CENTER)
            setSize(800, 600)
            setLocationRelativeTo(null)
            isVisible = true
        }
    }
}