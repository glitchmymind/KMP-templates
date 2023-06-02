//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.DisposableEffect
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.lifecycle.Lifecycle.Event.*
//import androidx.lifecycle.LifecycleEventObserver
//
//@Composable
//fun ObserveLifecycle(
//    onCreate: (() -> Unit)? = null,
//    onStart: (() -> Unit)? = null,
//    onResume: (() -> Unit)? = null,
//    onPause: (() -> Unit)? = null,
//    onStop: (() -> Unit)? = null,
//    onDestroy: (() -> Unit)? = null,
//) {
//    val lifecycle = LocalLifecycleOwner.current.lifecycle
//    DisposableEffect(lifecycle) {
//        val lifecycleObserver = LifecycleEventObserver { _, event ->
//            when (event) {
//                ON_CREATE -> onCreate?.invoke()
//                ON_START -> onStart?.invoke()
//                ON_RESUME -> onResume?.invoke()
//                ON_PAUSE -> onPause?.invoke()
//                ON_STOP -> onStop?.invoke()
//                ON_DESTROY -> onDestroy?.invoke()
//                else -> Unit
//            }
//        }
//        lifecycle.addObserver(lifecycleObserver)
//        onDispose { lifecycle.removeObserver(lifecycleObserver) }
//    }
//}
