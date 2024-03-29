package net.ascend.lib.command.sub;

import net.ascend.lib.CC;
import net.ascend.lib.util.NumberUtil;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

@Getter
public abstract class SubCommand {

    protected final String name;
    protected final List<String> allies;
    protected final String permission;

    @Setter private boolean executeAsync;

    protected final boolean isPlayerOnly;

    public SubCommand(String name) {
        this(name, new ArrayList<>());
    }

    public SubCommand(String name, List<String> allies) {
        this(name, allies, null);
    }

    public SubCommand(String name, boolean isPlayerOnly) {
        this(name, new ArrayList<>(), isPlayerOnly);
    }

    public SubCommand(String name, String permission) {
        this(name, new ArrayList<>(), permission);
    }

    public SubCommand(String name, List<String> allies, String permission) {
        this(name, allies, permission, false);
    }

    public SubCommand(String name, String permission, boolean isPlayerOnly) {
        this(name, new ArrayList<>(), permission, isPlayerOnly);
    }

    public SubCommand(String name, List<String> allies, boolean isPlayerOnly) {
        this(name, allies, null, isPlayerOnly);
    }

    public SubCommand(String name, List<String> allies, String permission, boolean isPlayerOnly) {
        this.name = name;
        this.allies = allies;
        this.permission = permission;
        this.isPlayerOnly = isPlayerOnly;
    }

    protected boolean checkConsoleSender(CommandSender sender) {
        if(sender instanceof ConsoleCommandSender) {
            sender.sendMessage(CC.translate("&cYou cannot execute this command in console!"));
            return false;
        }
        return true;
    }

    protected boolean checkOfflinePlayer(CommandSender sender, OfflinePlayer offlinePlayer, String name) {
        if(!offlinePlayer.hasPlayedBefore() && !offlinePlayer.isOnline()) {
            sender.sendMessage(CC.translate("&cThe player by the name of '&c&l" + name + "&c' is currently not connected to the server!"));
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
        if(NumberUtil.getInt(number) == null) {
            sender.sendMessage(CC.translate("&c" + number + " &eis not a valid number."));
            return false;
        }
        return true;
    }

    public abstract void execute(CommandSender sender, String[] args);
}