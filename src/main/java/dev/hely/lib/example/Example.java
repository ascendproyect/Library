package dev.hely.lib.example;

import com.google.common.collect.ImmutableList;
import dev.hely.lib.command.CommandManager;
import dev.hely.lib.handler.HandlerManager;
import dev.hely.lib.manager.Manager;
import dev.hely.lib.menu.MenuManager;
import dev.hely.lib.sound.SoundManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 * @Author Joako
 * @Date 6/1/2023 | 21:55
 **/

public class Example extends JavaPlugin {

    public static Example INSTANCE;
    private List<Manager> managerList;

    @Override
    public void onEnable() {
        INSTANCE = this;
        managerList = ImmutableList.of(
                SoundManager.INSTANCE,
                MenuManager.INSTANCE,
                HandlerManager.INSTANCE,
                CommandManager.INSTANCE
        );

        managerList.forEach(manager -> manager.onEnable(this));
    }

    @Override
    public void onDisable() {
        managerList.forEach(manager -> manager.onDisable(this));
    }
}
