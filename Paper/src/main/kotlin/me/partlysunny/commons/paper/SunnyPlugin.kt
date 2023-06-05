package me.partlysunny.commons.paper

import me.partlysunny.commandeer.CommandManager
import me.partlysunny.commons.ICommons
import me.partlysunny.configurate.Configurate
import org.bukkit.plugin.java.JavaPlugin

abstract class SunnyPlugin(protected val pluginName: String) : JavaPlugin(), ICommons {

    private var commandManager: CommandManager? = null;

    fun reload() {
        Configurate.instance().configManager().loadAll();
    }

    override fun onLoad() {
        ConsoleLogger.console("Loading $pluginName...")
        SunnyUtilsCore.init(this)
        internalRegisterCommands()
        internalRegisterConfigurations()
        ConsoleLogger.console("Loaded $pluginName!")
    }

    override fun onEnable() {
        ICommons.INSTANCE = this
        preEnable()
        ConsoleLogger.console("Enabling $pluginName...")

        //Register stuff
        internalRegisterListeners()
        reload()
        internalRegisterGuis()
        ConsoleLogger.console("Enabled $pluginName!")
        postEnable()
    }

    override fun onDisable() {
        disable()
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

    protected fun preEnable() {
    }

    protected fun postEnable() {
    }

    protected fun disable() {
    }

    abstract fun registerListeners();
    abstract fun registerCommands(commandManager: CommandManager);
    abstract fun registerGuis();
    abstract fun registerConfigurations();

    override fun pluginClass(): Class<Any> {
        return this.javaClass
    }

}