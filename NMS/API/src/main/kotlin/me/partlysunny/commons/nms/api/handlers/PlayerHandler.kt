package me.partlysunny.commons.nms.api.handlers

import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.time.Duration

abstract class PlayerHandler(plugin: JavaPlugin) : SunnyNMSHandler(plugin) {
    abstract fun sendActionBar(player: Player, message: String)
    abstract fun setCooldown(player: Player, item: Material, ticks: Int)
    abstract fun setAbsorption(player: Player, amount: Double)
    abstract fun getAbsorption(player: Player): Double
    abstract fun forceRespawn(player: Player)
    abstract fun sendTitle(
        player: Player,
        title: String,
        subtitle: String,
        fadeIn: Duration,
        stay: Duration,
        fadeOut: Duration
    )
}
