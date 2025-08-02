package me.tizo.drillPickaxe;

import me.tizo.drillPickaxe.listener.BlockBreakListener;
import org.bstats.bukkit.Metrics;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class DrillPickaxe extends JavaPlugin {

    public static NamespacedKey drillKey;

    @Override
    public void onEnable() {
        drillKey = new NamespacedKey(this, "drill");
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);

        // bstats
        int pluginId = 26655;
        Metrics metrics = new Metrics(this, pluginId);
    }

    @Override
    public void onDisable() {
        BlockBreakListener.clearTracking();
    }
}