package net.ascend.lib.menu;

import net.ascend.lib.CC;
import net.ascend.lib.maker.ItemMaker;
import net.ascend.lib.menu.button.Button;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitTask;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created By LeandroSSJ
 * Created on 28/11/2021
 */

@Getter @Setter
public abstract class Menu {

    private boolean closed = false;
    private boolean placeholder = false;

    private boolean autoUpdate = false;
    private boolean clickUpdate = false;

    private BukkitTask bukkitTask = null;

    private Button button = Button.getPlaceholder();

    private Map<Integer, Button> menuContent = new ConcurrentHashMap<>();

    public abstract String getName();
    public abstract String getTitle(Player player);

    public abstract Map<Integer, Button> getMenuContent(Player player);

    private ItemStack getItemStack(Player player, Button button) {
        ItemStack itemStack = button.getItemStack(player);
        ItemStack skullItem = ItemMaker.of("SKULL_ITEM").build();

        if (itemStack.getType() != skullItem.getType()) {
            ItemMeta itemMeta = itemStack.getItemMeta();

            if (itemMeta != null) {
                if (itemMeta.hasDisplayName()) {
                    itemMeta.setDisplayName(itemMeta.getDisplayName());
                }
            }

            itemStack.setItemMeta(itemMeta);
        }

        return itemStack;
    }

    public void openMenu(Player player) {
        menuContent = getMenuContent(player);

        Menu previousMenu = MenuManager.INSTANCE.getMenuData().get(player.getUniqueId());
        Inventory inventory = null;
        int size = getSize() == -1 ? getSite(menuContent) : getSize();
        boolean update = false;
        String name = CC.translate(getTitle(player));

        if (name.length() > 32) {
            name = name.substring(0, 32);
        }

        if (player.getOpenInventory() != null) {
            if (previousMenu == null) {
                player.closeInventory();
            } else {
                Inventory topInventory = player.getOpenInventory().getTopInventory();

                int previousSize = topInventory.getSize();

                if (player.getOpenInventory().getTitle().equals(name) && previousSize == size) {
                    inventory = player.getOpenInventory().getTopInventory();
                    update = true;
                } else {
                    previousMenu.setClosed(true);
                    player.closeInventory();
                }
            }
        }

        if (inventory == null) {
            inventory = Bukkit.getServer().createInventory(player, size, name);
        }

        inventory.clear();

        MenuManager.INSTANCE.getMenuData().put(player.getUniqueId(), this);

        for (Map.Entry<Integer, Button> buttonEntry : menuContent.entrySet()) {
            inventory.setItem(buttonEntry.getKey(), getItemStack(player, buttonEntry.getValue()));
        }

        if (placeholder) {
            for (int index = 0; index < size; index++) {
                if (menuContent.get(index) == null) {
                    menuContent.put(index, button);
                    inventory.setItem(index, button.getItemStack(player));
                }
            }
        }

        if (update) {
            player.updateInventory();
        } else {
            player.openInventory(inventory);
        }

        onOpen(player);
        setClosed(false);

        if (autoUpdate) {
            if (bukkitTask == null) {
                bukkitTask = MenuManager.INSTANCE.getPlugin().getServer().getScheduler().runTaskTimer(MenuManager.INSTANCE.getPlugin(), () -> openMenu(player), 20L, 20L);
            }
        }
    }

    public int getSize() {
        return -1;
    }

    public int getSite(Map<Integer, Button> buttons) {
        int highest = 0;

        for (int slot : buttons.keySet()) {
            if (slot > highest) {
                highest = slot;
            }
        }

        return (int) (Math.ceil((highest + 1) / 9D) * 9D);
    }

    public int getSlot(int x, int y) {
        return x + y * 9;
    }

    public void onOpen(Player player) {
    }

    public void onClose(Player player) {
    }

    public void onClickPlayerInventory(Player player, int slot, ClickType clickType) {
    }
}
