package me.partlysunny.commons.paper

class SunnyUtilsCore(val plugin: SunnyPlugin) {

    // Singleton
    companion object {
        private var instance: SunnyUtilsCore? = null

        fun getInstance(): SunnyUtilsCore {
            return instance!!
        }

        fun init(plugin: SunnyPlugin) {
            instance = SunnyUtilsCore(plugin)
        }
    }

}