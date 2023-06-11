import com.adeo.kviewmodel.BaseSharedViewModel
import di.Inject

class AuthReducer : BaseSharedViewModel<AuthState, Action, Event>(
    initialState = AuthState()
) {

    private val authRepository: AuthRepository = Inject.instance()

    override fun obtainEvent(viewEvent: Event) {
        when(viewEvent) {
            is Event.LoginChanged -> TODO()
            is Event.LoginClicked -> TODO()
            is Event.PasswordChanged -> TODO()
        }
    }
}