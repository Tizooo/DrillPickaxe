package me.tizo.drillPickaxe.util;

import me.tizo.drillPickaxe.DrillPickaxe;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ItemUtils {

    // give command: /minecraft:give @p minecraft:netherite_pickaxe[custom_data={PublicBukkitValues:{"drillpickaxe:drill":1b}}]
    public static boolean isDrillPickaxe(ItemStack item) {
        if (item == null || item.getType() != Material.NETHERITE_PICKAXE) return false;
        if (!item.hasItemMeta()) return false;

        PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        return container.getOrDefault(DrillPickaxe.drillKey, PersistentDataType.BOOLEAN, false);
    }
}
