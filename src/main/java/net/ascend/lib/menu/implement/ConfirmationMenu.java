package net.ascend.lib.menu.implement;

import net.ascend.lib.callback.Callback;
import net.ascend.lib.menu.Menu;
import net.ascend.lib.menu.button.Button;
import net.ascend.lib.menu.button.implement.ConfirmationButton;
import lombok.AllArgsConstructor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;


/**
 * Created By LeandroSSJ
 * Created on 28/11/2021
 */

@AllArgsConstructor
public class ConfirmationMenu extends Menu {

    private final String name;
    private final Callback<Boolean> callback;


    @Override
    public String getName() {
        return "Confirmation Menu";
    }

    @Override
    public String getTitle(Player player) {
        return name;
    }

    @Override
    public Map<Integer, Button> getMenuContent(Player player) {
        Map<Integer, Button> buttonContent = new HashMap<>();

        for (int x = 1; x < 4; x++) {
            for (int y = 0; y < 3; y++) {
                buttonContent.put(getSlot(x, y), new ConfirmationButton(true, callback));
                buttonContent.put(getSlot(8 - x, y), new ConfirmationButton(false, callback));
            }
        }

        return buttonContent;
    }
}