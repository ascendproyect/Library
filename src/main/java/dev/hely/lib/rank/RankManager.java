package dev.hely.lib.rank;

import dev.hely.lib.manager.Manager;
import dev.hely.lib.rank.example.ExampleRank;
import dev.hely.lib.util.PluginUtil;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created By LeandroSSJ
 * Created on 28/11/2021
 */
public enum RankManager implements Manager {

    INSTANCE;

    @Getter private Rank rankStorage;

    @Override
    public void onEnable(JavaPlugin plugin) {
        if (PluginUtil.isEnabled("LuckPerms")) {
            rankStorage = new ExampleRank();
        }else{
            rankStorage = new ExampleRank();
        }
    }
}
