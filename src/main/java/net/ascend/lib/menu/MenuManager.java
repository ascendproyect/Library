package net.ascend.lib.menu;

import net.ascend.lib.manager.Manager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created By LeandroSSJ
 * Created on 28/11/2021
 */

@Getter
public enum MenuManager implements Manager {

    INSTANCE;

    private final Map<UUID, Menu> menuData = new ConcurrentHashMap<>();

    private JavaPlugin plugin;

    @Override
    public void onEnable(JavaPlugin plugin) {
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(new MenuListener(plugin), plugin);
    }
}
