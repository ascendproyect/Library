package net.ascend.lib.menu.button.implement;


import net.ascend.lib.maker.ItemMaker;
import net.ascend.lib.menu.button.Button;
import net.ascend.lib.menu.implement.GlobalPageMenu;
import net.ascend.lib.menu.implement.PaginatedMenu;
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
public class InformationButton extends Button {

    private final PaginatedMenu menu;

    @Override
    public ItemStack getItemStack(Player player) {
        return ItemMaker.of("QUARTZ_BLOCK").setDisplayName("&aPage #" + menu.getPage() + " out of " + menu.getPage(player)).build();
    }

    @Override
    public void onClick(Player player, int slot, ClickType clickType) {
        if (clickType == ClickType.RIGHT) {
            new GlobalPageMenu(menu).openMenu(player);
        }
    }
}
