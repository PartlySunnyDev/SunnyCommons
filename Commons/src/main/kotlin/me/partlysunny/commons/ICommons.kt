package me.partlysunny.commons

interface ICommons {

    fun pluginClass(): Class<Any>

    companion object {
        var INSTANCE: ICommons? = null
    }

}