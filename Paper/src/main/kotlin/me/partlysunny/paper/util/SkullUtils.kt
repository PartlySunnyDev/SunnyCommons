package me.partlysunny.paper.util;

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.mojang.authlib.GameProfile
import com.mojang.authlib.properties.Property
import me.partlysunny.commons.util.CommonUtils
import me.partlysunny.commons.util.reflection.JavaAccessor
import me.partlysunny.paper.util.ItemUtils.getAllVersionStack
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.SkullMeta
import java.util.*

object SkullUtils {

    /**
     * With this method you can get a player's head by nickname or a base64 head by base64 code
     *
     * @param type  Determines whether you want to get the head by name or by base64
     * @param value If you want a player's head, then the player's name. If you want base64, then base64 code.
     * @return Head itemStack
     */
    fun convert(type: HeadType, value: String): ItemStack {
        return if (type == HeadType.PLAYER_HEAD) {
            getSkullByTexture(getPlayerHeadTexture(value))
        } else {
            getSkullByTexture(value)
        }
    }

    private fun getSkullByTexture(url: String): ItemStack {
        val head = getAllVersionStack("SKULL_ITEM", "PLAYER_HEAD")
        if (url.isEmpty() || url == "none") return head
        val meta = head.itemMeta as SkullMeta?
        val profile = GameProfile(UUID.randomUUID(), "")
        profile.properties.put("textures", Property("textures", url))
        try {
            JavaAccessor.setValue(meta, JavaAccessor.getField(meta!!.javaClass, "profile"), profile)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        } catch (e: SecurityException) {
            e.printStackTrace()
        }
        head.setItemMeta(meta)
        return head
    }

    private fun getPlayerHeadTexture(username: String): String {
        if (getPlayerId(username) == "none") return "none"
        val url = "https://api.minetools.eu/profile/" + getPlayerId(username)
        return try {
            val userData = CommonUtils.readUrl(url)
            val parsedData: Any = JsonParser.parseString(userData)
            val jsonData = parsedData as JsonObject
            val decoded = jsonData["raw"] as JsonObject
            val textures = decoded["properties"] as JsonArray
            val data = textures[0] as JsonObject
            data["value"].toString()
        } catch (ex: Exception) {
            "none"
        }
    }

    private fun getPlayerId(playerName: String): String {
        return try {
            val url = "https://api.minetools.eu/uuid/$playerName"
            val userData = CommonUtils.readUrl(url)
            val parsedData: Any = JsonParser.parseString(userData)
            val jsonData = parsedData as JsonObject
            if (jsonData["id"] != null) jsonData["id"].toString() else ""
        } catch (ex: Exception) {
            "none"
        }
    }

    /**
     * Generation head type enum
     */
    enum class HeadType {
        PLAYER_HEAD,
        BASE64
    }

}
