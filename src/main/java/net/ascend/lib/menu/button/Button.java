package net.ascend.lib.menu.button;

import net.ascend.lib.maker.ItemMaker;
import net.ascend.lib.sound.SoundManager;
import lombok.AllArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created By LeandroSSJ
 * Created on 28/11/2021
 */
public abstract class Button {

    public abstract ItemStack getItemStack(Player player);

    public void onClick(InventoryClickEvent event) {
    }

    public void onClick(Player player, int slot, ClickType clickType) {
    }

    public boolean toShift(Player player, int slot, ClickType clickType) {
        return true;
    }

    public boolean toCancel(Player player, int slot, ClickType clickType) {
        return true;
    }

    public boolean toUpdate(Player player, int slot, ClickType clickType) {
        return false;
    }

    public static Button getPlaceholder() {
        return new Button() {
            public ItemStack getItemStack(Player player) {
                return ItemMaker.of("STAINED_GLASS_PANE", 1, 7).setDisplayName(" ").build();            }
        };
    }

    public static Button getPlaceholder(String material, int data, String title) {
        return new Button() {
            @Override
            public ItemStack getItemStack(Player player) {
                return ItemMaker.of(material, 1, data).setDisplayName(title).build();
            }
        };
    }

    @AllArgsConstructor
    public enum Sound {


        NEUTRAL(org.bukkit.Sound.valueOf("CLICK")),
        NEGATIVE(org.bukkit.Sound.valueOf("ANVIL_LAND")),
        AFFIRMATIVE(org.bukkit.Sound.valueOf("LEVEL_UP"));

        private final org.bukkit.Sound sound;

        public void toPlayer(Player player) {
            SoundManager.INSTANCE.playSound(player, sound.name());
        }
    }
}