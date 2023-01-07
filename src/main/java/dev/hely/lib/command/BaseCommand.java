package dev.hely.lib.command;

import dev.hely.lib.CC;
import dev.hely.lib.JavaUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created By LeandroSSJ
 * Created on 28/08/2021
 */
public abstract class BaseCommand extends BukkitCommand {

    @Getter private final boolean isPlayerOnly;
    @Setter @Getter private boolean executeAsync;

    public BaseCommand(String name) {
        this(name, new ArrayList<>());
    }

    public BaseCommand(String name, List<String> aliases) {
        this(name, aliases, false);
    }

    public BaseCommand(String name, String permission) {
        this(name, new ArrayList<>(), permission);
    }

    public BaseCommand(String name, boolean isPlayerOnly) {
        this(name, new ArrayList<>(), null, isPlayerOnly);
    }

    public BaseCommand(String name, List<String> aliases, String permission) {
        this(name, aliases, permission, false);
    }

    public BaseCommand(String name, List<String> aliases, boolean isPlayerOnly) {
        this(name, aliases, null, isPlayerOnly);
    }

    public BaseCommand(String name, String permission, boolean isPlayerOnly) {
        this(name, new ArrayList<>(), permission, isPlayerOnly);
    }

    public BaseCommand(String name, List<String> aliases, String permission, boolean isPlayerOnly) {
        super(name);

        this.setAliases(aliases);
        this.setPermission(permission);
        this.isPlayerOnly = isPlayerOnly;
    }

    protected boolean checkOfflinePlayer(CommandSender sender, OfflinePlayer offlinePlayer, String name) {
        if (!offlinePlayer.hasPlayedBefore() && !offlinePlayer.isOnline()) {
            sender.sendMessage(CC.translate("&cThe player by the name of '&c&l" + name + "&c' has never connected to the server.."));
            return false;
        }
        return true;
    }

    protected boolean checkPlayer(CommandSender sender, Player player, String name) {
        if(player == null) {
            sender.sendMessage(CC.translate("&cThe player by the name of '&c&l" + name + "&c' is currently not connected to the server!"));
            return false;
        }
        return true;
    }

    protected boolean checkNumber(CommandSender sender, String number) {
        if (JavaUtil.tryParseInt(number) == null) {
            sender.sendMessage(CC.translate("&cYou can only use integer values in this command usage!"));
            return false;
        }
        return true;
    }


    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(isPlayerOnly() && sender instanceof ConsoleCommandSender) {
            sender.sendMessage(CC.translate("&cYou cannot execute this command in console!"));
            return true;
        }

        if(this.getPermission() != null && !sender.hasPermission(this.getPermission())) {
            sender.sendMessage(CC.translate("&cYou do not have sufficient permissions to execute this command."));
            return true;
        }

        this.execute(sender, args);

        return true;
    }

    public abstract void execute(CommandSender sender, String[] args);
}