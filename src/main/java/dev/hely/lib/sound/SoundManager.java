package dev.hely.lib.sound;

import com.cryptomorin.xseries.XSound;
import dev.hely.lib.manager.Manager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

/**
 * Created By LeandroSSJ
 * Created on 24/02/2023
 * Protect Name Library
 */
public enum SoundManager implements Manager {

    INSTANCE;

    public void playSound(Player player, String sound) {
        try {
            if (Bukkit.getVersion().contains("1.7")) {
                player.playSound(player.getLocation(), Sound.valueOf(sound), 1.0f, 1.0f);
            } else {
                player.playSound(player.getLocation(), XSound.matchXSound(sound).get().parseSound(), 1.0f, 1.0f);

            }
        } catch (Exception exception) {
            Bukkit.getConsoleSender().sendMessage("Invalid Sound Name: " + sound.toUpperCase());
        }
    }
}
