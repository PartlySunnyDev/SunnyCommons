package me.partlysunny.paper.util

import me.partlysunny.paper.SunnyUtilsCore
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffectType
import org.bukkit.potion.PotionType
import java.util.regex.Pattern

object PaperUtils {

    fun scheduleRepeatingCancelTask(r: Runnable?, delay: Long, repeat: Long, stopAfter: Long) {
        val scheduler = Bukkit.getScheduler()
        val p: JavaPlugin = JavaPlugin.getPlugin(SunnyUtilsCore.getInstance().plugin::class.java)
        val t = scheduler.runTaskTimer(p, r!!, delay, repeat)
        scheduler.runTaskLater(p, Runnable { t.cancel() }, stopAfter)
    }

    fun <T> getOrDefault(y: ConfigurationSection, key: String?, def: T): T? {
        return if (y.contains(key!!)) {
            y[key] as T?
        } else def
    }

    fun <T> getOrError(y: ConfigurationSection, key: String): T? {
        if (y.contains(key)) {
            return y[key] as T?
        }
        throw IllegalArgumentException("Key " + key + " inside " + y.name + " was not found!")
    }

    @JvmOverloads
    fun splitLoreForLine(
        input: String,
        linePrefix: String = ChatColor.GRAY.toString(),
        lineSuffix: String = "",
        width: Int = 30
    ): List<String> {
        val array = input.toCharArray()
        val out: MutableList<String> = ArrayList()
        var currentColor = ""
        var cachedColor = ""
        var wasColorChar = false
        var currentLine = StringBuilder(linePrefix)
        var currentWord = StringBuilder()
        for (i in array.indices) {
            val c = array[i]
            if (wasColorChar) {
                wasColorChar = false
                cachedColor = currentColor
                val pattern = Pattern.compile("[0-9a-fkmolnr]")
                if (pattern.matcher(c.toString() + "").matches()) {
                    if (c == 'r') {
                        currentColor = ChatColor.COLOR_CHAR.toString() + "r"
                    } else {
                        currentColor += ChatColor.COLOR_CHAR.toString() + "" + c
                    }
                }
                currentWord.append(ChatColor.COLOR_CHAR.toString() + "").append(c)
                continue
            }
            if (c == '\n') {
                currentLine.append(currentWord)
                currentWord = StringBuilder()
                out.add(currentLine.toString() + lineSuffix)
                currentLine = StringBuilder(linePrefix + cachedColor + currentWord)
                cachedColor = currentColor
                continue
            }
            if (c == ' ') {
                if ((currentLine.toString() + currentWord.toString()).replace(
                        "ยง[0-9a-fklmnor]".toRegex(),
                        ""
                    ).length > width
                ) {
                    out.add(currentLine.toString() + lineSuffix)
                    currentLine = StringBuilder("$linePrefix$cachedColor$currentWord ")
                } else {
                    currentLine.append(currentWord).append(" ")
                }
                cachedColor = currentColor
                currentWord = StringBuilder()
                continue
            }
            if (c == ChatColor.COLOR_CHAR) {
                wasColorChar = true
                continue
            }
            currentWord.append(c)
        }
        currentLine.append(currentWord)
        out.add(currentLine.toString() + lineSuffix)
        return out
    }

    fun invalid(message: String, p: Player) {
        p.sendMessage(ChatColor.RED.toString() + message)
        p.playSound(p.location, Sound.ENTITY_ENDERMAN_TELEPORT, 1f, 1f)
    }

    fun asType(t: PotionEffectType?): PotionType {
        if (t == null) {
            return PotionType.WATER
        }
        var asType = PotionType.WATER
        for (type in PotionType.values()) {
            if (t == type.effectType) asType = type
        }
        return asType
    }

    fun hasAllPerms(p: Player, vararg perms: String): Boolean {
        for (perm in perms) {
            if (!p.hasPermission(perm)) {
                return false
            }
        }
        return true
    }

}