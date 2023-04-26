package me.partlysunny.commons.paper.util

import me.partlysunny.commons.paper.SunnyUtilsCore
import net.kyori.adventure.text.minimessage.MiniMessage
import org.bukkit.Bukkit
import org.bukkit.Sound
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.potion.PotionEffectType
import org.bukkit.potion.PotionType
import org.bukkit.util.ChatPaginator

object PaperUtils {

    fun scheduleRepeatingCancelTask(r: Runnable?, delay: Long, repeat: Long, stopAfter: Long) {
        val scheduler = Bukkit.getScheduler()
        val p: JavaPlugin = JavaPlugin.getPlugin(SunnyUtilsCore.getInstance().plugin::class.java)
        val t = scheduler.runTaskTimer(p, r!!, delay, repeat)
        scheduler.runTaskLater(p, Runnable { t.cancel() }, stopAfter)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getOrDefault(y: ConfigurationSection, key: String?, def: T): T? {
        return if (y.contains(key!!)) {
            y[key] as T?
        } else def
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getOrError(y: ConfigurationSection, key: String): T? {
        if (y.contains(key)) {
            return y[key] as T?
        }
        throw IllegalArgumentException("Key " + key + " inside " + y.name + " was not found!")
    }

    @JvmOverloads
    fun splitLoreForLine(
        input: String,
        linePrefix: String = "<gray>",
        lineSuffix: String = "",
        width: Int = 30
    ): List<String> {
        val wordWrap = ChatPaginator.wordWrap(input, width).toList()
        wordWrap.map { linePrefix + it + lineSuffix }
        return wordWrap
    }

    fun invalid(message: String, p: Player) {
        p.sendMessage(MiniMessage.miniMessage().deserialize("<red>$message"))
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