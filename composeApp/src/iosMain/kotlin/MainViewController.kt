import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import database.getUserDatabase

fun MainViewController() = ComposeUIViewController {
    val database = remember { getUserDatabase() }
    App(database)
}