package me.tizo.drillPickaxe.listener;

import me.tizo.drillPickaxe.util.BlockUtils;
import me.tizo.drillPickaxe.util.ItemUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BlockBreakListener implements Listener {

    private static final Set<Location> activeBlocksBroken = new HashSet<>();

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        Block center = event.getBlock();
        Location loc = center.getLocation();

        if (activeBlocksBroken.remove(loc)) return;

        Player player = event.getPlayer();
        if (player.isSneaking()) return;

        ItemStack item = player.getInventory().getItemInMainHand();
        if (!ItemUtils.isDrillPickaxe(item)) return;

        Vector face = BlockUtils.getBreakingFace(player);
        if (face == null) {
            face = new Vector(0, 1, 0);
        }

        List<Block> blocks = BlockUtils.getAffectedBlocks(center, face);
        Material centerType = center.getType();

        for (Block b : blocks) {
            if (b.equals(center)) continue;
            if (b.getType() == centerType) {
                activeBlocksBroken.add(b.getLocation());
                player.breakBlock(b);
            }
        }
    }

    public static void clearTracking() {
        activeBlocksBroken.clear();
    }
}
