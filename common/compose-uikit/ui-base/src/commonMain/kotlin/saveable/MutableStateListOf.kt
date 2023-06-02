import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList

@Composable
fun <T : Any> rememberSaveableMutableStateListOf(vararg elements: T): SnapshotStateList<T> {
    return rememberSaveable(
        saver =
            listSaver(
                save = { stateList ->
                    check(stateList.all { canBeSaved(it) }) {
                        "rememberSaveableMutableStateListOf must be used only with lists of saveable types"
                    }
                    stateList.toList()
                },
                restore = { list -> list.toMutableStateList() },
            ),
    ) { elements.toList().toMutableStateList() }
}
