package dev.hely.lib;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;

/**
 * @Author Joako
 * @Date 5/1/2023 | 20:20
 **/

public class InventoryUtil {
    public static ItemStack[] deserializeItemStackArray(String s) {
        try {
            BukkitObjectInputStream bukkitObjectInputStream = new BukkitObjectInputStream(new ByteArrayInputStream(Base64Coder.decodeLines(s)));
            ItemStack[] array = new ItemStack[bukkitObjectInputStream.readInt()];
            for (int i = 0; i < array.length; ++i) {
                array[i] = (ItemStack) bukkitObjectInputStream.readObject();
            }
            bukkitObjectInputStream.close();
            return array;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(ex.getCause().toString());
            return new ItemStack[0];
        }
    }
}
