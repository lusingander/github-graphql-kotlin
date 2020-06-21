package com.github.lusingander

// from: http://snowtooth.moonhighway.com/

fun buildQueryFoo(): ObjectNode {
    return query {
        allLifts(status = LiftStatus.OPEN) {
            id
            name
            status
            capacity
            trailAccess {
                id
                name
                status
                difficulty
            }
        }
    }
}

class LIFT(private val __id: String): ObjectNode("Lift") {
    val id get() =
        ScalarNode("id").also { doInit(it) }
    val name get() =
        ScalarNode("name").also { doInit(it) }
    val status get() =
        ScalarNode("status").also { doInit(it) }
    val capacity get() =
        ScalarNode("capacity").also { doInit(it) }
    val night get() =
        ScalarNode("night").also { doInit(it) }
    val elevationGain get() =
        ScalarNode("elevationGain").also { doInit(it) }
    fun trailAccess(init: TRAIL_ACCESS.() -> Unit) =
        doInit(TRAIL_ACCESS(), init)

    override fun toString() =
        "$__name(id: $__id) { ${children.joinToString(" ")} }"
}

class ALL_LIFTS(private val __status: LiftStatus?): ObjectNode("allLifts") {
    val id get() =
        ScalarNode("id").also { doInit(it) }
    val name get() =
        ScalarNode("name").also { doInit(it) }
    val status get() =
        ScalarNode("status").also { doInit(it) }
    val capacity get() =
        ScalarNode("capacity").also { doInit(it) }
    val night get() =
        ScalarNode("night").also { doInit(it) }
    val elevationGain get() =
        ScalarNode("elevationGain").also { doInit(it) }
    fun trailAccess(init: TRAIL_ACCESS.() -> Unit) =
        doInit(TRAIL_ACCESS(), init)

    override fun toString() =
        "$__name${searchQuery()} { ${children.joinToString(" ")} }"

    private fun searchQuery() =
        if (__status != null) {
            "(status: $__status)"
        } else {
            ""
        }
}

enum class LiftStatus {
    OPEN,
    CLOSED,
    HOLD,
    ;
}

class TRAIL(private val __id: String): ObjectNode("Trail") {
    val id get() =
        ScalarNode("id").also { doInit(it) }
    val name get() =
        ScalarNode("name").also { doInit(it) }
    val status get() =
        ScalarNode("status").also { doInit(it) }
    val difficulty get() =
        ScalarNode("difficulty").also { doInit(it) }
    val groomed get() =
        ScalarNode("groomed").also { doInit(it) }
    val tress get() =
        ScalarNode("tress").also { doInit(it) }
    val night get() =
        ScalarNode("night").also { doInit(it) }
    fun accessedByLifts(init: ACCESSED_BY_LIFTS.() -> Unit) =
        doInit(ACCESSED_BY_LIFTS(), init)

    override fun toString() =
        "$__name(id: $__id) { ${children.joinToString(" ")} }"
}

class ALL_TRAILS(private val __status: TrailStatus?): ObjectNode("allTrails") {
    val id get() =
        ScalarNode("id").also { doInit(it) }
    val name get() =
        ScalarNode("name").also { doInit(it) }
    val status get() =
        ScalarNode("status").also { doInit(it) }
    val difficulty get() =
        ScalarNode("difficulty").also { doInit(it) }
    val groomed get() =
        ScalarNode("groomed").also { doInit(it) }
    val tress get() =
        ScalarNode("tress").also { doInit(it) }
    val night get() =
        ScalarNode("night").also { doInit(it) }
    fun accessedByLifts(init: ACCESSED_BY_LIFTS.() -> Unit) =
        doInit(ACCESSED_BY_LIFTS(), init)

    override fun toString() =
        "$__name${searchQuery()} { ${children.joinToString(" ")} }"

    private fun searchQuery() =
        if (__status != null) {
            "(status: $__status)"
        } else {
            ""
        }
}

enum class TrailStatus {
    OPEN,
    CLOSED,
    ;
}

class TRAIL_ACCESS: ObjectNode("trailAccess") {
    val id get() =
        ScalarNode("id").also { doInit(it) }
    val name get() =
        ScalarNode("name").also { doInit(it) }
    val status get() =
        ScalarNode("status").also { doInit(it) }
    val difficulty get() =
        ScalarNode("difficulty").also { doInit(it) }
    val groomed get() =
        ScalarNode("groomed").also { doInit(it) }
    val tress get() =
        ScalarNode("tress").also { doInit(it) }
    val night get() =
        ScalarNode("night").also { doInit(it) }
    fun accessedByLifts(init: ACCESSED_BY_LIFTS.() -> Unit) =
        doInit(ACCESSED_BY_LIFTS(), init)
}

class ACCESSED_BY_LIFTS(): ObjectNode("accessedByLifts") {
    val id get() =
        ScalarNode("id").also { doInit(it) }
    val name get() =
        ScalarNode("name").also { doInit(it) }
    val status get() =
        ScalarNode("status").also { doInit(it) }
    val capacity get() =
        ScalarNode("capacity").also { doInit(it) }
    val night get() =
        ScalarNode("night").also { doInit(it) }
    val elevationGain get() =
        ScalarNode("elevationGain").also { doInit(it) }
    fun trailAccess(init: TRAIL_ACCESS.() -> Unit) =
        doInit(TRAIL_ACCESS(), init)
}

class QUERY: ObjectNode("query") {
    fun allLifts(status: LiftStatus? = null, init: ALL_LIFTS.() -> Unit) =
        doInit(ALL_LIFTS(status), init)
    fun allTrails(status: TrailStatus? = null, init: ALL_TRAILS.() -> Unit) =
        doInit(ALL_TRAILS(status), init)
    fun Lift(id: String, init: LIFT.() -> Unit) =
        doInit(LIFT(id), init)
    fun Trail(id: String, init: TRAIL.() -> Unit) =
        doInit(TRAIL(id), init)
}

fun query(init: QUERY.() -> Unit) = QUERY().apply(init)

