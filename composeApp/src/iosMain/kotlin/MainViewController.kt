import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.bayu07750.albumsplaceholderkmp.AppContainer
import com.bayu07750.albumsplaceholderkmp.ui.App

fun MainViewController() = ComposeUIViewController {
    App(
        appContainer = remember { AppContainer() }
    )
}