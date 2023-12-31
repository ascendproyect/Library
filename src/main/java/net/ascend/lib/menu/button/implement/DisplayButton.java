package net.ascend.lib.menu.button.implement;

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
public class DisplayButton extends Button {

    private final boolean cancel;
    private final ItemStack itemStack;

    @Override
    public ItemStack getItemStack(Player player) {
        return itemStack == null ? new ItemStack(Material.AIR) : itemStack;
    }

    @Override
    public boolean toCancel(Player player, int slot, ClickType clickType) {
        return cancel;
    }
}
