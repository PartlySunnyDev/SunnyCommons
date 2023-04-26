package me.partlysunny.commons.paper

import me.partlysunny.commandeer.CommandManager
import me.partlysunny.configurate.Configurate
import org.bukkit.plugin.java.JavaPlugin

abstract class SunnyPlugin(protected val pluginName: String) : JavaPlugin() {

    private var commandManager: CommandManager? = null;

    protected fun reload() {
        Configurate.instance().configManager().loadAll();
    }

    override fun onLoad() {
        SunnyUtilsCore.init(this)
    }

    override fun onEnable() {
        ConsoleLogger.console("Loading $pluginName...")

        //Register stuff
        internalRegisterCommands()
        internalRegisterListeners()
        reload()
        internalRegisterGuis()
        ConsoleLogger.console("Enabled $pluginName!")
    }

    override fun onDisable() {
        ConsoleLogger.console("Disabling $pluginName...")
    }

    private fun internalRegisterGuis() {
        registerGuis()
    }

    private fun internalRegisterCommands() {
        commandManager = CommandManager(this)
        registerCommands(commandManager!!)
    }

    private fun internalRegisterListeners() {
        registerListeners()
    }

    private fun internalRegisterConfigurations() {
        Configurate.initialize(this)
        registerConfigurations()
    }

    abstract fun registerListeners();
    abstract fun registerCommands(commandManager: CommandManager);
    abstract fun registerGuis();
    abstract fun registerConfigurations();

}