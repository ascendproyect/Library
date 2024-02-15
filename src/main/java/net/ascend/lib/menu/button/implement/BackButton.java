package net.ascend.lib.menu.button.implement;


import net.ascend.lib.maker.ItemMaker;
import net.ascend.lib.menu.Menu;
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
public class BackButton extends Button {

    private final Menu menu;

    @Override
    public ItemStack getItemStack(Player player) {
        return ItemMaker.of("REDSTONE_BLOCK")
                .setDisplayName("&cBack").build();
    }

    @Override
    public void onClick(Player player, int slot, ClickType clickType) {
        menu.openMenu(player);
    }
}
