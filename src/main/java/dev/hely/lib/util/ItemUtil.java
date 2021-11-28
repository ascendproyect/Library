package dev.hely.lib.util;

import lombok.experimental.UtilityClass;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Created By LeandroSSJ
 * Created on 15/08/2021
 */

@UtilityClass
public class ItemUtil {

    public static void removeItem(Player player) {
        if(player.getItemInHand().getAmount() > 1) {
            player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
            return;
        }

        player.getInventory().setItemInHand(new ItemStack(Material.AIR));
        player.updateInventory();
    }

}
