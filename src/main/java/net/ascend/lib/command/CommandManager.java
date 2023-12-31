package net.ascend.lib.command;

import net.ascend.lib.manager.Manager;
import net.ascend.lib.wrapper.ClassWrapper;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.java.JavaPlugin;

public enum CommandManager implements Manager {
    INSTANCE;

    private CommandMap commandMap;
    private String pluginName;


    @Override
    public void onEnable(JavaPlugin plugin) {
        commandMap = getCommandMap();
        pluginName = plugin.getDescription().getName();
    }


    private CommandMap getCommandMap() {
        return (CommandMap) new ClassWrapper(Bukkit.getServer()).getField("commandMap").get();
    }

    public void registerCommand(BukkitCommand command) {
        this.commandMap.register(pluginName, command);
    }
}
