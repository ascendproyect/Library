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
public class PageButton extends Button {

    private final int mod;
    private final PaginatedMenu menu;

    @Override
    public ItemStack getItemStack(Player player) {
        if (mod > 0) {
            if (hasNext(player)) {
                return ItemMaker.of("CARPET", 1, 5)
                        .setDisplayName("&aNext Page").build();
            } else {
                return ItemMaker.of("CARPET", 1, 7)
                        .setDisplayName("&cLast Page").build();
            }
        } else {
            if (hasPrevious(player)) {
                return ItemMaker.of("CARPET", 1, 14)
                        .setDisplayName("&aPrevious Page").build();
            } else {
                return ItemMaker.of("CARPET", 1, 7)
                        .setDisplayName("&cFirst Page").build();
            }
        }
    }


    @Override
    public void onClick(Player player, int slot, ClickType clickType) {
        if (mod > 0) {
            if (hasNext(player)) {
                menu.setPage(player, mod);
            }
        } else {
            if (hasPrevious(player)) {
                menu.setPage(player, mod);
            }
        }
    }

    private boolean hasNext(Player player) {
        int page = menu.getPage() + mod;
        return menu.getPage(player) >= page;
    }

    private boolean hasPrevious(Player player) {
        int page = menu.getPage() + mod;
        return page > 0;
    }
}