package dev.hely.lib.menu.implement;

import dev.hely.lib.Callback;
import dev.hely.lib.menu.Menu;
import dev.hely.lib.menu.button.Button;
import dev.hely.lib.menu.button.implement.ConfirmationButton;
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
    public String getName(Player player) {
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