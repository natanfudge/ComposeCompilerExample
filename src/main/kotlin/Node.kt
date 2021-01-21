
data class Node(
    // An example configuration of a node, this can be anything, ints, strings, modifiers, etc.
    val tag: String
) {
    var parent: Node? = null

    private val children: MutableList<Node> = mutableListOf()

    fun insertAt(index: Int, instance: Node) {
        children.add(index, instance)
        instance.parent = this
    }

    fun removeAll() {
        children.clear()
    }

    fun move(from: Int, to: Int, count: Int) {
        if (from > to) {
            var current = to
            repeat(count) {
                val node = children[from]
                children.removeAt(from)
                children.add(current, node)
                current++
            }
        } else {
            repeat(count) {
                val node = children[from]
                children.removeAt(from)
                children.add(to - 1, node)
            }
        }
    }

    fun remove(index: Int, count: Int) {
        repeat(count) {
            val instance = children.removeAt(index)
            instance.parent = null
        }
    }

    internal fun toConsoleTree(depth: Int = 0): String {
        return " ".repeat(depth) + "{$tag}" +
                if (children.isNotEmpty()) ": [\n" + children.joinToString(",\n") { it.toConsoleTree(depth + 1) } + "\n" + " ".repeat(
                    depth
                ) + "]"
                else ""
    }
}
