package net.ascend.lib.menu;

import net.ascend.lib.menu.button.Button;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created By LeandroSSJ
 * Created on 28/11/2021
 */

@AllArgsConstructor
public class MenuListener implements Listener {

    private final JavaPlugin plugin;

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getWhoClicked() instanceof Player) {
            int slot = event.getSlot();

            Player player = (Player) event.getWhoClicked();
            ClickType clickType = event.getClick();

            Menu openMenu = MenuManager.INSTANCE.getMenuData().get(player.getUniqueId());

            if (openMenu != null) {
                if (slot != event.getRawSlot()) {
                    openMenu.onClickPlayerInventory(player, event.getSlot(), event.getClick());
                    event.setCancelled(true);
                    return;
                }

                if (openMenu.getMenuContent().containsKey(slot)) {
                    Button button = openMenu.getMenuContent().get(slot);

                    boolean shift = button.toShift(player, slot, clickType);
                    boolean cancel = button.toCancel(player, slot, clickType);

                    if (clickType == ClickType.SHIFT_LEFT || clickType == ClickType.SHIFT_RIGHT) {
                        event.setCancelled(shift);
                    } else {
                        event.setCancelled(cancel);
                    }

                    button.onClick(event);
                    button.onClick(player, slot, clickType);

                    boolean update = button.toUpdate(player, slot, clickType);

                    if (openMenu.isClickUpdate() || update) {
                        if (!openMenu.isClosed()) {
                            openMenu.openMenu(player);
                        }
                    }

                    if (event.isCancelled()) {
                        new BukkitRunnable() {
                            public void run() {
                                player.updateInventory();
                            }
                        }.runTaskLaterAsynchronously(plugin, 5L);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();
        Menu openMenu = MenuManager.INSTANCE.getMenuData().get(player.getUniqueId());

        if (openMenu != null) {
            openMenu.onClose(player);
            if (openMenu.getBukkitTask() != null) {
                openMenu.getBukkitTask().cancel();
            }
            MenuManager.INSTANCE.getMenuData().remove(player.getUniqueId());
        }
    }
}