import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.awt.ComposePanel
import api.generateGraph
import color.LocalBackgroundColor
import ru.alexgladkov.odyssey.compose.base.Navigator
import ru.alexgladkov.odyssey.compose.local.LocalRootController
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.alexgladkov.odyssey.compose.navigation.modal_navigation.ModalNavigator
import theme.UiKitTheme
import javax.swing.JFrame
import javax.swing.WindowConstants
import java.awt.BorderLayout

fun JFrame.setupThemedNavigation() {
    val rootController = RootComposeBuilder().apply {
        generateGraph(NavigationSource.Desktop)
    }.build()

    defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
    title = "PlayZone Admin"

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

    contentPane.add(composePanel, BorderLayout.CENTER)
    setSize(800, 600)
    setLocationRelativeTo(null)
    isVisible = true
}