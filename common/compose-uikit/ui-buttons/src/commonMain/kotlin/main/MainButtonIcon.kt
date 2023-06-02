import androidx.compose.runtime.Immutable

@Immutable
sealed interface MainButtonIcon {

    @Immutable data class Loadable(val url: String) : MainButtonIcon

//    @Immutable data class Icon(val id: Int) : MainButtonIcon
//    @Immutable data class Icon(@DrawableRes val id: Int) : MainButtonIcon

    @Immutable data class Icon(val name: String) : MainButtonIcon

    @Immutable data class Image(val name: String) : MainButtonIcon
//    @Immutable data class Image(@DrawableRes val id: Int) : MainButtonIcon
}
