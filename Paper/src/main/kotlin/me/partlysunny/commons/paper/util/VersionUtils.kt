package me.partlysunny.commons.paper.util

import me.partlysunny.commons.paper.ConsoleLogger
import org.bukkit.Bukkit
import org.bukkit.Server

object VersionUtils {
    init {
        val version = Bukkit.getBukkitVersion()
        if (version.contains("-pre") || version.contains("-rc")) {
            ConsoleLogger.warn("You are using a pre-release version of Bukkit. This may cause issues with SunnyCommons.")
        }
    }

    val mcVersion: String
        /**
         * @return Formatted like 1.19.3
         */
        get() {
            val version = Bukkit.getBukkitVersion()
            val dashIndex = version.indexOf('-')
            return version.substring(0, dashIndex)
        }

    val nmsVersion: String
        /**
         * @return Formatted like 1_19_R3
         */
        get() {
            val server = Bukkit.getServer()
            val serverClass: Class<out Server> = server.javaClass
            val serverPackage = serverClass.getPackage()
            val serverPackageName = serverPackage.name
            val lastPeriod = serverPackageName.lastIndexOf('.')
            val nextIndex = lastPeriod + 1 /* +1 to skip the period */ + 1 /* +1 to skip the 'v' */
            return serverPackageName.substring(nextIndex)
        }

    val simpleVersion: String
        /**
         * @return Something like 1.19
         */
        get() {
            val minecraftVersion = mcVersion
            val lastPeriodIndex = minecraftVersion.lastIndexOf('.')
            return if (lastPeriodIndex < 2) minecraftVersion else minecraftVersion.substring(0, lastPeriodIndex)
        }

    val minorVersion: Int
        /**
         * @return Something like 19 from 1.19
         */
        get() {
            return simpleVersion.substring(simpleVersion.indexOf('.') + 1).toInt()
        }
}