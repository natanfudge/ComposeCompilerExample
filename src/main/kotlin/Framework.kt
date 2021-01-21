import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.Snapshot
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.util.*


private val listeners = mutableListOf<(String) -> Unit>()
fun onConsoleInput(listener: (String) -> Unit) {
    // Will only work with exactly one listener, for simplicity
    if (listeners.isEmpty()) listeners.add(listener)
}

@OptIn(ExperimentalComposeApi::class)
suspend fun compose(
    composable: @Composable() () -> Unit
) : Unit = coroutineScope {
    val root = Node("<root>")

    Snapshot.registerGlobalWriteObserver { Snapshot.sendApplyNotifications() }

    val composition = compositionFor(
        root,
        NodeChangeApplier(root) { displayNewTree(root) },
        Recomposer.current()
    ) {
        println("Created Composition!")
    }

    composition.setContent {
        composable()
    }

    launch {
        while (true) {
            val input = Scanner(System.`in`).next()
            if (input == "STOP") {
                composition.dispose()
                break
            }
            for (listener in listeners) {
                listener(input)
            }
        }
    }
}

private fun displayNewTree(root: Node) {
    println(root.toConsoleTree())
}

@Composable
fun node(
    tagName: String,
    repeatAmount: Int = 1,
    children: @Composable() () -> Unit,
) {

    emit<Node, NodeChangeApplier>(
        factory = { Node(tagName) },
        update = {
            // Use this to set modifiers, if you decide to make a similar system, i.e.
            // set(modifier) { modifier -> this.modifier = modifier }
        },
        content = {
            repeat(repeatAmount) {
                children()
            }
        }
    )
}



