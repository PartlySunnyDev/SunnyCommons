package me.partlysunny.commons.paper.util.classes

import me.partlysunny.commons.classes.builders.HashMapBuilder
import org.bukkit.enchantments.Enchantment

enum class EnchantBundle(private val bundle: HashMap<Enchantment, Int>) {
    BLANK(HashMap<Enchantment, Int>()),
    DURABILITY(
        HashMapBuilder.builder(Enchantment::class.java, Int::class.java)
            .put(Enchantment.DURABILITY, 3).put(Enchantment.MENDING, 1).build()
    ),
    ARMOR_HELM(
        HashMapBuilder.builder(Enchantment::class.java, Int::class.java)
            .put(Enchantment.PROTECTION_ENVIRONMENTAL, 4).put(Enchantment.DURABILITY, 3)
            .put(Enchantment.WATER_WORKER, 1).put(Enchantment.OXYGEN, 3).put(Enchantment.MENDING, 1).build()
    ),
    ARMOR_CHESTPLATE(
        HashMapBuilder.builder(Enchantment::class.java, Int::class.java)
            .put(Enchantment.PROTECTION_ENVIRONMENTAL, 4).put(Enchantment.DURABILITY, 3).put(Enchantment.MENDING, 1)
            .build()
    ),
    ARMOR_LEGGINGS(
        HashMapBuilder.builder(Enchantment::class.java, Int::class.java)
            .put(Enchantment.PROTECTION_ENVIRONMENTAL, 4).put(Enchantment.DURABILITY, 3).put(Enchantment.MENDING, 1)
            .build()
    ),
    ARMOR_BOOTS(
        HashMapBuilder.builder(Enchantment::class.java, Int::class.java)
            .put(Enchantment.PROTECTION_ENVIRONMENTAL, 4).put(Enchantment.DURABILITY, 3)
            .put(Enchantment.PROTECTION_FALL, 1).put(Enchantment.DEPTH_STRIDER, 3).put(Enchantment.MENDING, 1).build()
    ),
    WEAPON_FIRE(
        HashMapBuilder.builder(Enchantment::class.java, Int::class.java)
            .put(Enchantment.DAMAGE_ALL, 4).put(Enchantment.DURABILITY, 3).put(Enchantment.FIRE_ASPECT, 2)
            .put(Enchantment.SWEEPING_EDGE, 3).put(Enchantment.LOOT_BONUS_MOBS, 3).put(Enchantment.MENDING, 1).build()
    ),
    WEAPON(
        HashMapBuilder.builder(Enchantment::class.java, Int::class.java)
            .put(Enchantment.DAMAGE_ALL, 4).put(Enchantment.DURABILITY, 3).put(Enchantment.SWEEPING_EDGE, 3)
            .put(Enchantment.LOOT_BONUS_MOBS, 3).put(Enchantment.MENDING, 1).build()
    ),
    PICKAXE_FORTUNE(
        HashMapBuilder.builder(Enchantment::class.java, Int::class.java)
            .put(Enchantment.DIG_SPEED, 5).put(Enchantment.DURABILITY, 3).put(Enchantment.LOOT_BONUS_BLOCKS, 3)
            .put(Enchantment.MENDING, 1).build()
    ),
    PICKAXE_SILK(
        HashMapBuilder.builder(Enchantment::class.java, Int::class.java)
            .put(Enchantment.DIG_SPEED, 5).put(Enchantment.DURABILITY, 3).put(Enchantment.SILK_TOUCH, 3)
            .put(Enchantment.MENDING, 1).build()
    );

    fun bundle(): HashMap<Enchantment, Int> {
        return bundle
    }
}
