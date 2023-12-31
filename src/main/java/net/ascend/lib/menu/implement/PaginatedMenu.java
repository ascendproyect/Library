package net.ascend.lib.menu.implement;


import net.ascend.lib.menu.Menu;
import net.ascend.lib.menu.button.Button;
import net.ascend.lib.menu.button.implement.InformationButton;
import net.ascend.lib.menu.button.implement.PageButton;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created By LeandroSSJ
 * Created on 28/11/2021
 */

@Getter @Setter
public abstract class PaginatedMenu extends Menu {

    public int page = 1;

    public void setPage(Player player, int amount) {
        page += amount;

        getMenuContent().clear();
        openMenu(player);
    }

    public int getPage(Player player) {
        int amount = getPaginatedContent(player).size();

        if (amount == 0) {
            return 1;
        }

        return Double.valueOf(Math.ceil(amount / Integer.valueOf(getMaxPerPage(player)).doubleValue())).intValue();
    }

    public int getMaxPerPage(Player player) {
        return 27;
    }

    @Override
    public Map<Integer, Button> getMenuContent(Player player) {
        /*int min = Double.valueOf(Integer.valueOf((page - 1) * getMaxPerPage(player)).doubleValue()).intValue();
        int max = Double.valueOf(Integer.valueOf((page) * getMaxPerPage(player)).doubleValue()).intValue();

        int top = 0;

        Map<Integer, Button> menuContent = new ConcurrentHashMap<>();

        for (Map.Entry<Integer, Button> entry : getPaginatedContent(player).entrySet()) {
            int ind = entry.getKey();

            if (ind >= min && ind < max) {
                ind -= Double.valueOf(Integer.valueOf((getMaxPerPage(player)) * (page - 1)).doubleValue() - 9).intValue();

                menuContent.put(ind, entry.getValue());

                if (ind > top) {
                    top = ind;
                }
            }
        }*/

        int minIndex = (int)((this.page - 1) * (double)this.getMaxPerPage(player));
        int maxIndex = (int)(this.page * (double)this.getMaxPerPage(player));
        maxIndex += 4;
        Map<Integer, Button> menuContent = new ConcurrentHashMap<>();
        AtomicInteger index = new AtomicInteger(0);
        int numberToAdd = this.page * 10 + 1;

        for (Map.Entry<Integer, Button> entry : getPaginatedContent(player).entrySet()) {
            int ind = index.getAndIncrement();
            if (ind >= minIndex && ind < maxIndex) {
                ind -= (int)(this.getMaxPerPage(player) * (double)(this.page - 1)) - 10;
                if (ind == 16) {
                    index.set((this.page == 1) ? 9 : (9 + numberToAdd));
                }
                if (ind == 25) {
                    index.set((this.page == 1) ? 18 : (18 + numberToAdd));
                }
                menuContent.put(ind, entry.getValue());
            }
        }


        Map<Integer, Button> global = getGlobalContent(player);

        for (Map.Entry<Integer, Button> entry : global.entrySet()) {
            menuContent.put(entry.getKey(), entry.getValue());
        }

        return menuContent;
    }
    @Override
    public boolean isClickUpdate() {
        return false;
    }

    public Map<Integer, Button> getGlobalContent(Player player) {
        Map<Integer, Button> menuContent = new HashMap<>();

        menuContent.put(0, Button.getPlaceholder());
        menuContent.put(1, new PageButton(-1, this));
        menuContent.put(2, Button.getPlaceholder());

        menuContent.put(3, Button.getPlaceholder());
        menuContent.put(4, new InformationButton(this));
        menuContent.put(5, Button.getPlaceholder());

        menuContent.put(6, Button.getPlaceholder());
        menuContent.put(7, new PageButton(1, this));
        menuContent.put(8, Button.getPlaceholder());

        menuContent.put(9, Button.getPlaceholder());
        menuContent.put(17, Button.getPlaceholder());
        menuContent.put(18, Button.getPlaceholder());
        menuContent.put(26, Button.getPlaceholder());
        menuContent.put(27, Button.getPlaceholder());
        menuContent.put(35, Button.getPlaceholder());

        menuContent.put(36, Button.getPlaceholder());
        menuContent.put(37, new PageButton(-1, this));
        menuContent.put(38, Button.getPlaceholder());

        menuContent.put(39, Button.getPlaceholder());
        menuContent.put(40, new InformationButton(this));
        menuContent.put(41, Button.getPlaceholder());

        menuContent.put(42, Button.getPlaceholder());
        menuContent.put(43, new PageButton(1, this));
        menuContent.put(44, Button.getPlaceholder());

        return menuContent;
    }

    public abstract Map<Integer, Button> getPaginatedContent(Player player);
}