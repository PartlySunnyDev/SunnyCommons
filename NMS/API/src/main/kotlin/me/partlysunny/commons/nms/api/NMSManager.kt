package me.partlysunny.commons.nms.api

import me.partlysunny.commons.nms.api.handlers.PlayerHandler
import me.partlysunny.commons.paper.util.VersionUtils
import org.bukkit.plugin.java.JavaPlugin

class NMSManager(private val plugin: JavaPlugin) {

    private lateinit var playerHandler: PlayerHandler

    private fun load() {
        playerHandler = loadModuleHandler(PlayerHandler::class.java) ?: throw IllegalStateException("No PlayerHandler found for this version of Minecraft");
    }

    private fun <T> loadModuleHandler(handlerClass: Class<T>): T? {
        val nmsVersion = VersionUtils.nmsVersion
        val nmsModulePackage = "me.partlysunny.commons.nms.v$nmsVersion"
        val handlerName = handlerClass.simpleName
        val handlerClassName = "${nmsModulePackage}.${handlerName}_${nmsVersion}"
        val versionSpecificHandlerClass = Class.forName(handlerClassName)
        return try {
            val newInstance = versionSpecificHandlerClass.getDeclaredConstructor(JavaPlugin::class.java).newInstance(plugin)
            handlerClass.cast(newInstance)
        } catch (e: Exception) {
            null;
        }
    }

    init {
        load()
    }

    fun getPlayerHandler(): PlayerHandler {
        return playerHandler
    }

}
