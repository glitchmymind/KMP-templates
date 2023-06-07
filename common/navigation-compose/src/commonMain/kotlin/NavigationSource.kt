sealed interface NavigationSource {
    object Android : NavigationSource
    object Desktop : NavigationSource
    object Web : NavigationSource
    object Ios : NavigationSource
}