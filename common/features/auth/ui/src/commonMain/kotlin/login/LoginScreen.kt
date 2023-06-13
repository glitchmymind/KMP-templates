package login

import AuthReducer
import AuthState
import androidx.compose.runtime.Composable
import com.adeo.kviewmodel.compose.observeAsState
import com.adeo.kviewmodel.odyssey.StoredViewModel

@Composable
fun LoginScreen() {
//
//    StoredViewModel(factory = { AuthReducer() }) { viewModel ->
//        val state = viewModel.viewStates().observeAsState()
//        val action = viewModel.viewActions().observeAsState()
//
//        LoginView(state.value, viewModel::obtainEvent)
        LoginView(AuthState()) {

        }
//
//        when (action.value) {
//            is Action.OpenMainScreen -> {
//
////                rootController.findRootController()
////                    .present(
////                        screen = NavigationTree.Main.Dashboard.name,
////                        launchFlag = LaunchFlag.SingleNewTask
////                    )
//            }
//
//            else -> {} // smell
//        }
//    }
}
