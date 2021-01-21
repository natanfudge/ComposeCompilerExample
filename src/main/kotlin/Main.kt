import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember

suspend fun main() = compose {
    Tree()
}

@Composable
private fun Tree() {
    val messages = remember { mutableStateListOf<String>() }
    onConsoleInput {
        messages.add(it)
    }

    node("foo") {
        for (message in messages) {
            node(message) {}
        }
    }
}
