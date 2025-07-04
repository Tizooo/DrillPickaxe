package me.tizo.drillPickaxe;

import me.tizo.drillPickaxe.listener.BlockBreakListener;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public final class DrillPickaxe extends JavaPlugin implements Listener {

    public static NamespacedKey drillKey;

    @Override
    public void onEnable() {
        drillKey = new NamespacedKey(this, "drill");
        getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
    }

    @Override
    public void onDisable() {
        BlockBreakListener.clearTracking();
    }
}