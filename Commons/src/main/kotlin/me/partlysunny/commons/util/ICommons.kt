package me.partlysunny.commons.util

interface ICommons {

    fun enable()

    fun disable()

    fun pluginClass(): Class<Any>

    companion object {
        var INSTANCE: ICommons? = null
    }

}