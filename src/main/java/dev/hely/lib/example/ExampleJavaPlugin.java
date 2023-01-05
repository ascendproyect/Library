package dev.hely.lib.example;

import com.google.common.collect.ImmutableList;
import dev.hely.lib.handler.HandlerManager;
import dev.hely.lib.manager.Manager;
import dev.hely.lib.menu.MenuManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 * Created By LeandroSSJ
 * Created on 28/11/2021
 */
public class ExampleJavaPlugin extends JavaPlugin {

    public static ExampleJavaPlugin INSTANCE;

    private List<Manager> managerList;

    @Override
    public void onLoad() {
        managerList = ImmutableList.of(
                MenuManager.INSTANCE,
                HandlerManager.INSTANCE
        );
    }

    @Override
    public void onEnable() {
        INSTANCE = this;

        managerList.forEach(manager -> manager.onEnable(this));
    }

    @Override
    public void onDisable() {
        managerList.forEach(manager -> manager.onDisable(this));
    }

    public void addManager(Manager manager) {
        this.managerList.add(manager);
    }
}
