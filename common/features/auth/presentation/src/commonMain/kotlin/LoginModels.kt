data class AuthState(
    val isLoading: Boolean = false,
)

sealed interface Event {
    data class LoginChanged(val value: String) : Event
    data class PasswordChanged(val value: String) : Event

    object LoginClicked : Event
}

sealed interface Action {
    object OpenMainScreen : Action
}
