import androidx.compose.runtime.AbstractApplier
import androidx.compose.runtime.ExperimentalComposeApi

@OptIn(ExperimentalComposeApi::class)
class NodeChangeApplier(
    root: Node,
    private val onChange: () -> Unit
) : AbstractApplier<Node>(root) {
    override fun insertBottomUp(index: Int, instance: Node) {
        current.insertAt(index, instance)
    }

    override fun insertTopDown(index: Int, instance: Node) {
        // ignored. Building tree bottom-up
    }

    override fun remove(index: Int, count: Int) {
        current.remove(index, count)
    }

    override fun move(from: Int, to: Int, count: Int) {
        current.move(from, to, count)
    }

    override fun onClear() {
        root.removeAll()
    }

    override fun onEndChanges() : Unit =  onChange()
}
