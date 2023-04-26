package me.partlysunny.commons.util

import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.net.URL
import java.nio.file.Files
import java.util.*

object CommonUtils {
    val RAND = Random()

    fun getRandomBetween(a: Int, b: Int): Int {
        require(a <= b) { "a must be higher than b" }
        if (a == b) {
            return a
        }
        if (a < 0 && b < 0) {
            return -getRandomBetween(-b, -a)
        }
        return if (a < 0) {
            getRandomBetween(0, -a + b) + a
        } else RAND.nextInt(b - a) + a
    }

    fun isInvalidFilePath(path: String?): Boolean {
        val f = File(path)
        return try {
            f.canonicalPath
            false
        } catch (e: IOException) {
            true
        }
    }

    @Throws(IOException::class)
    fun copy(source: String?, destination: File) {
        val stream = ICommons.INSTANCE!!.pluginClass().classLoader.getResourceAsStream(source)
        stream ?: throw IOException("Cannot find $source")
        if (!destination.exists()) {
            Files.copy(stream, destination.toPath())
        }
    }

    fun getAlphabetSorted(values: Array<String?>): Array<String?> {
        val strings: List<String?> = listOf(*values)
        strings.sortedWith { o1, o2 -> o1!!.compareTo(o2!!) }
        return strings.toTypedArray<String?>()
    }

    fun linspace(min: Double, max: Double, points: Int): DoubleArray {
        val d = DoubleArray(points)
        for (i in 0 until points) {
            d[i] = min + i * (max - min) / (points - 1)
        }
        return d
    }

    fun fakeSpace(points: Int): DoubleArray {
        return when (points) {
            0 -> doubleArrayOf()
            1 -> doubleArrayOf(4.0)
            2 -> doubleArrayOf(3.0, 5.0)
            3 -> doubleArrayOf(2.0, 4.0, 6.0)
            4 -> doubleArrayOf(1.0, 3.0, 5.0, 7.0)
            5 -> doubleArrayOf(2.0, 3.0, 4.0, 5.0, 6.0)
            6 -> doubleArrayOf(1.0, 2.0, 3.0, 5.0, 6.0, 7.0)
            7 -> doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0)
            8 -> doubleArrayOf(0.0, 1.0, 2.0, 3.0, 5.0, 6.0, 7.0, 8.0)
            else -> doubleArrayOf(0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0)
        }
    }

    @Throws(Exception::class)
    fun readUrl(urlString: String): String {
        var reader: BufferedReader? = null
        return try {
            val url = URL(urlString)
            reader = BufferedReader(InputStreamReader(url.openStream()))
            val buffer = StringBuilder()
            var read: Int
            val chars = CharArray(1024)
            while (reader.read(chars).also { read = it } != -1) buffer.append(chars, 0, read)
            buffer.toString()
        } finally {
            reader?.close()
        }
    }

    fun deleteFile(f: File) {
        if (f.exists() && !f.isDirectory) {
            f.delete()
        }
    }
}
