import androidx.compose.ui.semantics.SemanticsPropertyKey
import androidx.compose.ui.semantics.SemanticsPropertyReceiver

object SemanticsPropertiesExtended {

    val DrawableId: SemanticsPropertyKey<Int> = SemanticsPropertyKey(name = "DrawableId")

    val ImageUrl: SemanticsPropertyKey<String> = SemanticsPropertyKey(name = "ImageUrl")
}

var SemanticsPropertyReceiver.drawableId: Int by SemanticsPropertiesExtended.DrawableId

var SemanticsPropertyReceiver.imageUrl: String by SemanticsPropertiesExtended.ImageUrl
