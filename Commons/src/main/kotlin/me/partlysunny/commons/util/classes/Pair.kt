package me.partlysunny.commons.util.classes

class Pair<A, B>(private var a: A, private var b: B) {
    fun a(): A? {
        return a
    }

    fun setA(a: A) {
        this.a = a
    }

    fun b(): B? {
        return b
    }

    fun setB(b: B) {
        this.b = b
    }

    fun flushNulls(repA: A, repB: B) {
        if (a() == null) setA(repA)
        if (b() == null) setB(repB)
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
