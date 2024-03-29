package net.ascend.lib.menu.button.implement;

import net.ascend.lib.maker.ItemMaker;
import net.ascend.lib.menu.button.Button;
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
public class JumpToPageButton extends Button {

    private final int page;
    private final boolean current;

    private final PaginatedMenu menu;

    @Override
    public ItemStack getItemStack(Player player) {
        return ItemMaker.of(current ? "ENCHANTED_BOOK" : "BOOK")
                .setDisplayName("&aPage " + page).build();
    }

    @Override
    public void onClick(Player player, int slot, ClickType clickType) {
        menu.setPage(player, page - menu.getPage());
    }
}
