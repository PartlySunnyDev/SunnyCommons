package me.partlysunny.commons.paper.util.classes

import net.kyori.adventure.text.Component
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.OfflinePlayer
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta
import org.bukkit.inventory.meta.LeatherArmorMeta
import org.bukkit.inventory.meta.SkullMeta
import java.util.*
import java.util.function.BiConsumer
import java.util.function.Consumer


class ItemBuilder {
    private val dataManager: PersistentDataManager
    private var material: Material
    private var meta: ItemMeta
    private var amount = 1

    constructor(material: Material) {
        this.material = material
        val meta = ItemStack(material).itemMeta
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
        dataManager = PersistentDataManager(meta.persistentDataContainer)
        this.meta = meta
    }

    constructor(item: ItemStack) {
        material = item.type
        val meta = item.itemMeta
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
        this.meta = meta
        dataManager = PersistentDataManager(meta.persistentDataContainer)
    }

    constructor(skullOwner: OfflinePlayer?) {
        val temp = ItemBuilder(Material.PLAYER_HEAD).skullOwner(skullOwner).create()
        material = temp.type
        val meta = temp.itemMeta
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES)
        this.meta = meta
        dataManager = PersistentDataManager(meta.persistentDataContainer)
    }

    fun name(name: String): ItemBuilder {
        meta.displayName(Component.text(name!!))
        return this
    }

    fun name(name: Component?): ItemBuilder {
        meta.displayName(name)
        return this
    }

    @JvmOverloads
    fun loreString(lore: List<String>, add: Boolean = false): ItemBuilder {
        if (add) {
            var oldLore = meta.lore()
            if (oldLore == null) oldLore = listOf()
            oldLore.addAll(lore.stream().map { content: String ->
                Component.text(
                    content!!
                )
            }.toList())
            meta.lore(oldLore)
        } else {
            meta.lore(lore.stream().map { content: String ->
                Component.text(
                    content!!
                )
            }.toList())
        }
        return this
    }

    fun loreString(vararg lore: String): ItemBuilder {
        return loreString(Arrays.asList(*lore), false)
    }

    @JvmOverloads
    fun lore(lore: Collection<Component>, add: Boolean = false): ItemBuilder {
        if (add) {
            var oldLore = meta.lore()
            if (oldLore == null) oldLore = listOf()
            oldLore.addAll(lore)
            meta.lore(oldLore)
        } else {
            meta.lore(lore.toList())
        }
        return this
    }

    fun lore(vararg lore: Component): ItemBuilder {
        return lore(listOf(*lore), false)
    }

    fun clearLore(): ItemBuilder {
        meta.lore(listOf())
        return this
    }

    fun amount(amount: Int): ItemBuilder {
        this.amount = amount
        return this
    }

    fun material(material: Material): ItemBuilder {
        this.material = material
        return this
    }

    // TODO: You should probably wrap every PersistentDataManager method in the builder but im not bothered
    fun modifyFromData(consumer: BiConsumer<ItemBuilder?, PersistentDataManager?>): ItemBuilder {
        consumer.accept(this, dataManager)
        return this
    }

    fun nbtString(key: String, value: String): ItemBuilder {
        dataManager.writeString(key, value)
        return this
    }

    fun nbtInt(key: String, value: Int): ItemBuilder {
        dataManager.writeInt(key, value)
        return this
    }

    fun nbtDouble(key: String, value: Double): ItemBuilder {
        dataManager.writeDouble(key, value)
        return this
    }

    fun nbtLong(key: String, value: Long): ItemBuilder {
        dataManager.writeLong(key, value)
        return this
    }

    fun nbtBoolean(key: String, value: Boolean): ItemBuilder {
        dataManager.writeBoolean(key, value)
        return this
    }

    fun nbtShort(key: String, value: Short): ItemBuilder {
        dataManager.writeShort(key, value)
        return this
    }

    fun nbtByte(key: String, value: Byte): ItemBuilder {
        dataManager.writeByte(key, value)
        return this
    }

    fun clearNBT(): ItemBuilder {
        dataManager.clear()
        return this
    }

    fun skullOwner(player: OfflinePlayer?): ItemBuilder {
        val skullMeta = meta as SkullMeta
        skullMeta.setOwningPlayer(player)
        meta = skullMeta
        return this
    }

    fun leatherArmorColor(color: Color?): ItemBuilder {
        if (material == Material.LEATHER_HELMET || material == Material.LEATHER_CHESTPLATE || material == Material.LEATHER_LEGGINGS || material == Material.LEATHER_BOOTS) {
            val leatherArmorMeta = meta as LeatherArmorMeta
            leatherArmorMeta.setColor(color)
            meta = leatherArmorMeta
        }
        return this
    }

    fun leatherArmorColor(r: Int, g: Int, b: Int): ItemBuilder {
        return leatherArmorColor(Color.fromRGB(r, g, b))
    }

    fun leatherArmorColor(rgb: Int): ItemBuilder {
        return leatherArmorColor(Color.fromRGB(rgb))
    }

    fun leatherArmorColor(r: Double, g: Double, b: Double): ItemBuilder {
        return leatherArmorColor(Color.fromRGB((r * 255).toInt(), (g * 255).toInt(), (b * 255).toInt()))
    }

    // Automatically ignores level restrictions so no problems
    fun enchant(enchantment: Enchantment?, level: Int): ItemBuilder {
        meta.addEnchant(enchantment!!, level, true)
        return this
    }

    fun clearEnchants(): ItemBuilder {
        meta.enchants.keys.forEach(Consumer { ench: Enchantment? ->
            meta.removeEnchant(
                ench!!
            )
        })
        return this
    }

    fun unbreakable(unbreakable: Boolean): ItemBuilder {
        meta.isUnbreakable = unbreakable
        return this
    }

    fun addFlag(vararg flags: ItemFlag): ItemBuilder {
        meta.addItemFlags(*flags)
        return this
    }

    fun removeFlag(vararg flags: ItemFlag): ItemBuilder {
        meta.removeItemFlags(*flags)
        return this
    }

    fun clearFlags(): ItemBuilder {
        meta.removeItemFlags(*ItemFlag.values())
        return this
    }

    fun customModelData(customModelData: Int): ItemBuilder {
        meta.setCustomModelData(customModelData)
        return this
    }

    fun create(): ItemStack {
        val item = ItemStack(material, amount)
        item.setItemMeta(meta)
        return item
    }

    fun apply(initial: ItemStack) {
        initial.type = material
        initial.amount = amount
        initial.setItemMeta(meta)
    }
}
