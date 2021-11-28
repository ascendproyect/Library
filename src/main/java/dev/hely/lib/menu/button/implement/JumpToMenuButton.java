package dev.hely.lib.menu.button.implement;


import dev.hely.lib.menu.Menu;
import dev.hely.lib.menu.button.Button;
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
public class JumpToMenuButton extends Button {

    private final Menu menu;
    private final ItemStack itemStack;

    @Override
    public ItemStack getItemStack(Player player) {
        return itemStack == null ? new ItemStack(Material.AIR) : itemStack;
    }

    @Override
    public void onClick(Player player, int slot, ClickType clickType) {
        menu.openMenu(player);
    }
}
