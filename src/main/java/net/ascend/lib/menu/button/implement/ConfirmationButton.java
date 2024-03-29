package net.ascend.lib.menu.button.implement;

import net.ascend.lib.callback.Callback;
import net.ascend.lib.maker.ItemMaker;
import net.ascend.lib.menu.button.Button;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
/**
 * Created By LeandroSSJ
 * Created on 28/11/2021
 */

@AllArgsConstructor
public class ConfirmationButton extends Button {

    private final boolean confirm;
    private final Callback<Boolean> callback;

    @Override
    public ItemStack getItemStack(Player player) {
        return ItemMaker.of("WOOL", 1, confirm ? 5 : 14)
                .setDisplayName(confirm ? "&aConfirm" : "&cCancel").build();
    }

    @Override
    public void onClick(Player player, int slot, ClickType clickType) {
        if (confirm) {
            Sound.AFFIRMATIVE.toPlayer(player);
        } else {
            Sound.NEGATIVE.toPlayer(player);
        }

        player.closeInventory();

        callback.callback(confirm);
    }
}
