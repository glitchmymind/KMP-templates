//import android.content.res.Resources
//import android.graphics.Matrix
//import androidx.annotation.DrawableRes
//import androidx.annotation.XmlRes
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.ui.geometry.Size
//import androidx.compose.ui.graphics.Outline
//import androidx.compose.ui.graphics.Shape
//import androidx.compose.ui.graphics.asComposePath
//import androidx.compose.ui.unit.Density
//import androidx.compose.ui.unit.LayoutDirection
//import androidx.compose.ui.unit.dp
//import androidx.core.graphics.PathParser
//import vivid.money.android_core.drawable.SimpleVectorDrawableParser
//import vivid.money.kotlinutil.logNonFatal
//
///**
// * Take first path from vector drawable and create Shape. On some parse error will be used
// * [errorShape]
// */
//class VectorShape(
//    @XmlRes @DrawableRes private val drawableResId: Int,
//    private val resources: Resources,
//    private val errorShape: Shape = RoundedCornerShape(size = 8.dp),
//) : Shape {
//
//    private val vectorDrawableParser = SimpleVectorDrawableParser(resources)
//
//    override fun createOutline(
//        size: Size,
//        layoutDirection: LayoutDirection,
//        density: Density,
//    ): Outline {
//        var parseException: IllegalArgumentException? = null
//        val parsedVectorDrawable =
//            try {
//                vectorDrawableParser.parse(drawableResId = drawableResId)
//            } catch (e: IllegalArgumentException) {
//                parseException = e
//                null
//            }
//
//        return parsedVectorDrawable?.pathData?.firstOrNull()?.let { pathData ->
//            val path = PathParser.createPathFromPathData(pathData.path)
//            val scaleX = size.width / parsedVectorDrawable.viewportWidth
//            val scaleY = size.height / parsedVectorDrawable.viewportHeight
//            val scaleMatrix = Matrix().apply { setScale(scaleX, scaleY) }
//
//            path.transform(scaleMatrix)
//            Outline.Generic(path.asComposePath())
//        }
//            ?: errorShape.createOutline(size, layoutDirection, density).also {
//                logNonFatal(
//                    message =
//                        "VectorShape: Can't create ${Outline::class.qualifiedName} for resource \"${resources.getResourceName(drawableResId)}\"",
//                    throwable = parseException,
//                )
//            }
//    }
//}
