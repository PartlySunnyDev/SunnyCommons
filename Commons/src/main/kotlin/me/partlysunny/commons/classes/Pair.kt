package me.partlysunny.commons.classes

class Pair<A, B>(var a: A, var b: B) {
    fun flushNulls(repA: A, repB: B) {
        if (a == null) a = repA
        if (b == null) b = repB
    }

    override fun equals(other: Any?): Boolean {
        return if (other !is Pair<*, *>) {
            false
        } else other.a == a && other.b == b
    }

    override fun hashCode(): Int {
        var result = a?.hashCode() ?: 0
        result = 31 * result + (b?.hashCode() ?: 0)
        return result
    }
}
