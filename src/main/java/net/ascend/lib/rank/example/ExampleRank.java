package net.ascend.lib.rank.example;

import net.ascend.lib.rank.Rank;
import org.bukkit.entity.Player;

/**
 * Created By LeandroSSJ
 * Created on 28/11/2021
 */
public class ExampleRank implements Rank {
    @Override
    public String getRank(Player p0) {
        return "Example Rank Name";
    }

    @Override
    public String getPrefix(Player p0) {
        return "Example Rank Prefix";
    }

    @Override
    public String getSuffix(Player p0) {
        return "Example Rank Suffix";
    }
}
