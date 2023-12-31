package net.ascend.lib.menu.implement;

import net.ascend.lib.menu.Menu;
import net.ascend.lib.menu.button.Button;
import net.ascend.lib.menu.button.implement.BackButton;
import net.ascend.lib.menu.button.implement.JumpToPageButton;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created By LeandroSSJ
 * Created on 28/11/2021
 */

@AllArgsConstructor
public class GlobalPageMenu extends Menu {

    private final PaginatedMenu menu;

    @Override
    public String getName() {
        return "Global Page Menu";
    }

    @Override
    public String getTitle(Player player) {
        return "Jump to Page";
    }

    @Override
    public Map<Integer, Button> getMenuContent(Player player) {
        Map<Integer, Button> menuContent = new ConcurrentHashMap<>();

        for (int i = 0; i < 9; i++) {
            if (i != 4) {
                menuContent.put(i, Button.getPlaceholder());
            }
        }

        menuContent.put(4, new BackButton(menu));

        for (int i = 18; i < 27; i++) {
            if (i != 22) {
                menuContent.put(i, Button.getPlaceholder());
            }
        }

        menuContent.put(22, new BackButton(menu));

        int index = 9;

        for (int i = 1; i <= menu.getPage(player); i++) {
            menuContent.put(index++, new JumpToPageButton(i, menu.getPage() == i, menu));

            if ((index - 8) % 9 == 0) {
                index += 2;
            }
        }

        return menuContent;
    }

    @Override
    public boolean isAutoUpdate() {
        return true;
    }
}