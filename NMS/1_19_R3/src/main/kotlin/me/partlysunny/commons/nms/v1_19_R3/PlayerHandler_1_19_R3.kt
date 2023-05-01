package me.partlysunny.commons.nms.v1_19_R3;

import me.partlysunny.commons.nms.api.handlers.PlayerHandler
import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.title.Title
import net.kyori.adventure.title.TitlePart
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import java.time.Duration

class PlayerHandler_1_19_R3(plugin: JavaPlugin) : PlayerHandler(plugin) {
    override fun sendActionBar(player: Player, message: String) {
        player.sendActionBar(MiniMessage.miniMessage().deserialize(message))
    }

    override fun sendTitle(player: Player, title: String, subtitle: String, fadeIn: Duration, stay: Duration, fadeOut: Duration) {
        player.sendTitlePart(TitlePart.TITLE, MiniMessage.miniMessage().deserialize(title))
        player.sendTitlePart(TitlePart.SUBTITLE, MiniMessage.miniMessage().deserialize(subtitle))
        player.sendTitlePart(TitlePart.TIMES, Title.Times.times(fadeIn, stay, fadeOut))
    }

    override fun setCooldown(player: Player, item: Material, ticks: Int) {
        player.setCooldown(item, ticks)
    }

    override fun setAbsorption(player: Player, amount: Double) {
        player.absorptionAmount = amount
    }

    override fun getAbsorption(player: Player): Double {
        return player.absorptionAmount
    }

    override fun forceRespawn(player: Player) {
        if (player.isDead) {
            player.spigot().respawn()
        }
    }
}
