package net.ascend.lib.example;

import com.google.common.collect.ImmutableList;
import net.ascend.lib.command.CommandManager;
import net.ascend.lib.handler.HandlerManager;
import net.ascend.lib.manager.Manager;
import net.ascend.lib.menu.MenuManager;
import net.ascend.lib.sound.SoundManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

/**
 * @Author Joako
 * @Date 6/1/2023 | 21:55
 **/

public class Example extends JavaPlugin {

    private List<Manager> managerList;

    @Override
    public void onEnable() {
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
