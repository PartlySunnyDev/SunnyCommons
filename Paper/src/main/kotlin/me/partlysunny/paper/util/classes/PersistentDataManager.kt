package me.partlysunny.paper.util.classes

import org.bukkit.NamespacedKey
import org.bukkit.persistence.PersistentDataAdapterContext
import org.bukkit.persistence.PersistentDataContainer
import org.bukkit.persistence.PersistentDataType
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors

data class PersistentDataManager(val container: PersistentDataContainer) {
    fun readString(key: String): String? {
        return container.get(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.STRING)
    }

    fun writeString(key: String, value: String) {
        container.set(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.STRING, value)
    }

    fun readInt(key: String): Int? {
        return container.get(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.INTEGER)
    }

    fun writeInt(key: String, value: Int) {
        container.set(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.INTEGER, value)
    }

    fun readDouble(key: String): Double? {
        return container.get(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.DOUBLE)
    }

    fun writeDouble(key: String, value: Double) {
        container.set(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.DOUBLE, value)
    }

    fun readBoolean(key: String): Boolean {
        return container.get(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.BYTE)!!
            .toInt() == 1
    }

    fun writeBoolean(key: String, value: Boolean) {
        container.set(
            NamespacedKey.fromString("$NAMESPACE:$key")!!,
            PersistentDataType.BYTE,
            if (value) 1.toByte() else 0.toByte()
        )
    }

    fun readLong(key: String): Long? {
        return container.get(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.LONG)
    }

    fun writeLong(key: String, value: Long) {
        container.set(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.LONG, value)
    }

    fun readFloat(key: String): Float? {
        return container.get(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.FLOAT)
    }

    fun writeFloat(key: String, value: Float) {
        container.set(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.FLOAT, value)
    }

    fun readShort(key: String): Short? {
        return container.get(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.SHORT)
    }

    fun writeShort(key: String, value: Short) {
        container.set(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.SHORT, value)
    }

    fun readByte(key: String): Byte? {
        return container.get(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.BYTE)
    }

    fun writeByte(key: String, value: Byte) {
        container.set(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.BYTE, value)
    }

    fun readByteArray(key: String): ByteArray? {
        return container.get(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.BYTE_ARRAY)
    }

    fun writeByteArray(key: String, value: ByteArray) {
        container.set(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.BYTE_ARRAY, value)
    }

    fun readIntArray(key: String): IntArray? {
        return container.get(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.INTEGER_ARRAY)
    }

    fun writeIntArray(key: String, value: IntArray) {
        container.set(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.INTEGER_ARRAY, value)
    }

    fun readLongArray(key: String): LongArray? {
        return container.get(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.LONG_ARRAY)
    }

    fun writeLongArray(key: String, value: LongArray) {
        container.set(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.LONG_ARRAY, value)
    }

    fun readStringArray(key: String): Array<String> {
        return container.get(NamespacedKey.fromString("$NAMESPACE:$key")!!, STRING_ARRAY)!!
    }

    fun writeStringArray(key: String, value: Array<String>) {
        container.set(NamespacedKey.fromString("$NAMESPACE:$key")!!, STRING_ARRAY, value)
    }

    fun readContainer(key: String): PersistentDataContainer? {
        return container.get(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.TAG_CONTAINER)
    }

    fun writeContainer(key: String, value: PersistentDataContainer) {
        container.set(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.TAG_CONTAINER, value)
    }

    fun readContainers(key: String): Array<PersistentDataContainer> {
        return container.get(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.TAG_CONTAINER_ARRAY)!!
    }

    fun writeContainers(key: String, value: Array<PersistentDataContainer>) {
        container.set(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.TAG_CONTAINER_ARRAY, value)
    }

    fun has(key: String): Boolean {
        return container.has(NamespacedKey.fromString("$NAMESPACE:$key")!!, PersistentDataType.STRING)
    }

    fun remove(key: String) {
        container.remove(NamespacedKey.fromString("$NAMESPACE:$key")!!)
    }

    fun clear() {
        container.keys.forEach(Consumer { key: NamespacedKey? ->
            container.remove(
                key!!
            )
        })
    }

    fun write(key: String, value: Any) {
        when (value) {
            is String -> writeString(key, value)
            is Int -> writeInt(
                key,
                value
            )

            is Double -> writeDouble(
                key,
                value
            )

            is Boolean -> writeBoolean(key, value)
            is Long -> writeLong(
                key,
                value
            )

            is Float -> writeFloat(
                key,
                value
            )

            is Short -> writeShort(key, value)
            is Byte -> writeByte(
                key,
                value
            )

            is ByteArray -> writeByteArray(
                key,
                value
            )

            is IntArray -> writeIntArray(key, value)
            is LongArray -> writeLongArray(
                key,
                value
            )

            else -> throw IllegalArgumentException("Cannot write value of type " + value.javaClass.name)
        }
    }

    fun keys(): Set<String> {
        return container.keys.stream().map { obj: NamespacedKey -> obj.value() }.collect(Collectors.toSet())
    }

    operator fun iterator(): Iterator<String> {
        return container.keys.stream().map { obj: NamespacedKey -> obj.value() }.iterator()
    }

    companion object {
        private const val SEPARATOR = '|'
        private const val NAMESPACE = "realmmora"
        private val STRING_ARRAY: PersistentDataType<String, Array<String>> =
            object : PersistentDataType<String, Array<String>> {
                override fun getPrimitiveType(): Class<String> {
                    return String::class.java
                }

                override fun getComplexType(): Class<Array<String>> {
                    return Array<String>::class.java
                }

                override fun toPrimitive(complex: Array<String>, context: PersistentDataAdapterContext): String {
                    return Arrays.stream(complex).collect(Collectors.joining(SEPARATOR.toString()))
                }

                override fun fromPrimitive(primitive: String, context: PersistentDataAdapterContext): Array<String> {
                    return primitive.split(SEPARATOR.toString().toRegex()).dropLastWhile { it.isEmpty() }
                        .toTypedArray()
                }
            }
    }
}