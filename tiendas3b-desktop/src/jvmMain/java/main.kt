import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.shared.ShareApp
import di.initKoinDesktop

fun main() = application {
    initKoinDesktop()
    Window(onCloseRequest = ::exitApplication, title = "Tiendas3b") {
        ShareApp()
    }
}
