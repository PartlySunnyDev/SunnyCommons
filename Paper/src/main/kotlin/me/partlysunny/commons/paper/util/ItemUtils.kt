package me.partlysunny.commons.paper.util

import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object ItemUtils {

    fun setLore(i: ItemStack?, lore: List<Component?>?) {
        val m = i!!.itemMeta ?: return
        m.lore(lore)
        i.setItemMeta(m)
    }

    fun setName(i: ItemStack?, name: Component?) {
        val m = i!!.itemMeta ?: return
        m.displayName(name)
        i.setItemMeta(m)
    }

    fun addLoreLine(s: ItemStack, vararg lines: Component?) {
        val m = s.itemMeta
        var lore = m!!.lore()
        if (lore == null) {
            lore = ArrayList()
        }
        lore.addAll(listOf(*lines))
        m.lore(lore)
        s.setItemMeta(m)
    }

    fun getAllVersionStack(oldName: String, newName: String): ItemStack {
        val material: Material? = try {
            Material.valueOf(oldName)
        } catch (exception: Exception) {
            Material.valueOf(newName)
        }
        return ItemStack(material!!, 1)
    }

}