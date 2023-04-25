import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.LocalWindowExceptionHandlerFactory
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowExceptionHandler
import androidx.compose.ui.window.WindowExceptionHandlerFactory
import androidx.compose.ui.window.application

@OptIn(ExperimentalComposeUiApi::class)
fun main() = application {
    var libraryErrorMessage: String? by remember { mutableStateOf(null) }
    Window(onCloseRequest = ::exitApplication) {
        Column(Modifier.fillMaxSize()) {
            Text("Top level Application")
            libraryErrorMessage?.let {
                Text("Library error: $it")
            }
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

                Box(Modifier.background(Color.LightGray).padding(20.dp)) {
                    CompositionLocalProvider(
                        LocalWindowExceptionHandlerFactory provides WindowExceptionHandlerFactory {
                            WindowExceptionHandler {
                                println("catch exception")
                                libraryErrorMessage = it.message
                            }
                        }
                    ) {

                        Library1()

                    }
                }

            }
        }
    }
}

@Composable
fun Library1() {
    Button({
        throw Error("Simulate error")
    }) {
        Text("Throw exception")
    }
}
