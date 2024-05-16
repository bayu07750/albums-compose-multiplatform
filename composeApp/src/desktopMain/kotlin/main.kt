import androidx.compose.runtime.remember
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.bayu07750.albumsplaceholderkmp.AppContainer
import com.bayu07750.albumsplaceholderkmp.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "albums-placeholder-kmp",
    ) {
        App(
            appContainer = remember { AppContainer() }
        )
    }
}