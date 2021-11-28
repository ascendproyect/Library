package dev.hely.lib.rank;

import org.bukkit.entity.Player;

/**
 * Created By LeandroSSJ
 * Created on 28/11/2021
 */

public interface Rank {

    String getRank(Player p0);

    String getPrefix(Player p0);

    String getSuffix(Player p0);

}
