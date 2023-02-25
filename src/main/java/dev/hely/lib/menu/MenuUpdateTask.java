package dev.hely.lib.menu;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ConcurrentModificationException;

/**
 * Created By LeandroSSJ
 * Created on 24/02/2023
 * Protect Name Library
 */
public class MenuUpdateTask extends BukkitRunnable {

    private final JavaPlugin plugin;
    private final MenuManager menuManager;

    public MenuUpdateTask(JavaPlugin plugin, MenuManager menuManager){
        this.plugin = plugin;
        this.menuManager = menuManager;

        this.runTaskTimerAsynchronously(plugin, 20L, 20L);
    }

    @Override
    public void run() {
        try {
            menuManager.getMenuData().forEach((key, value) -> {
                Player player = Bukkit.getPlayer(key);

                if (player != null) {
                    if (value.isAutoUpdate()) {
                        value.openMenu(player);
                    }
                }
            });
        } catch (ConcurrentModificationException ignored) { }
    }
}
