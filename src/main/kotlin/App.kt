package com.github.lusingander

fun main() {
    println(buildQuery())
}

fun buildQuery(): ObjectNode {
    return repository {
        name
        description
        owner {
            login
        }
        stargazers(first = 10) {
            totalCount
            nodes {
                name
            }
        }
    }
}

open class ObjectNode(val __name: String) {
    val children = mutableListOf<ObjectNode>()

    fun <T : ObjectNode> doInit(child: T, init: T.() -> Unit = {}) {
        child.init()
        children.add(child)
    }

    override fun toString() =
        "$__name { ${children.joinToString(" ")} }"
}

open class ScalarNode(__name: String): ObjectNode(__name) {
    override fun toString() = __name
}

class REPOSITORY: ObjectNode("repository") {
    val name get() =
        NAME().also { doInit(it) }
    val description get() =
        DESCRIPTION().also { doInit(it) }
    fun owner(init: OWNER.() -> Unit) =
        doInit(OWNER(), init)
    fun stargazers(first: Int = 0, init: STARGAZERS.() -> Unit) =
        doInit(STARGAZERS(first), init)
}

class NAME: ScalarNode("name")

class DESCRIPTION: ScalarNode("description")

class OWNER: ObjectNode("owner") {
    val login get() =
        LOGIN().also { doInit(it) }
}

class LOGIN: ScalarNode("login")

class STARGAZERS(private val first: Int): ObjectNode("stargazers") {
    val totalCount get() =
        TOTAL_COUNT().also { doInit(it) }
    fun nodes(init: STARGAZERS_NODES.() -> Unit) =
        doInit(STARGAZERS_NODES(), init)

    override fun toString() =
        "$__name(first: $first) { ${children.joinToString(" ")} }"
}

class TOTAL_COUNT: ScalarNode("totalCount")

class STARGAZERS_NODES: ObjectNode("nodes") {
    val name get() =
        NAME().also { doInit(it) }
}

fun repository(init: REPOSITORY.() -> Unit) = REPOSITORY().apply(init)
