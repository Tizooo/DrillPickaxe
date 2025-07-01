package me.tizo.drillPickaxe.util;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BlockUtils {

    private static final int[][] DIRECTIONS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1},{1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
    private static final Set<Material> TRANSPARENT_MATERIALS = Set.of(Material.AIR, Material.CAVE_AIR, Material.WATER, Material.LAVA);

    public static Vector getBreakingFace(Player player) {
        List<Block> lastTwoTargetBlocks = player.getLastTwoTargetBlocks(TRANSPARENT_MATERIALS, 10);
        if (lastTwoTargetBlocks.size() != 2 || !lastTwoTargetBlocks.get(1).getType().isOccluding()) {
            return null;
        }
        Block targetBlock = lastTwoTargetBlocks.get(1);
        Block adjacentBlock = lastTwoTargetBlocks.get(0);
        BlockFace face = targetBlock.getFace(adjacentBlock);

        if (face == null) return null;

        return new Vector(face.getModX(), face.getModY(), face.getModZ());
    }

    public static List<Block> getAffectedBlocks(Block center, Vector face) {
        List<Block> blocks = new ArrayList<>(9);

        boolean xDominant = Math.abs(face.getX()) > Math.abs(face.getY()) && Math.abs(face.getX()) > Math.abs(face.getZ());
        boolean yDominant = Math.abs(face.getY()) > Math.abs(face.getZ());

        for (int[] offset : DIRECTIONS) {
            Block relative;
            if (xDominant) {
                relative = center.getRelative(0, offset[0], offset[1]);
            } else if (yDominant) {
                relative = center.getRelative(offset[0], 0, offset[1]);
            } else {
                relative = center.getRelative(offset[0], offset[1], 0);
            }
            blocks.add(relative);
        }

        blocks.add(center); // include center block
        return blocks;
    }
}
