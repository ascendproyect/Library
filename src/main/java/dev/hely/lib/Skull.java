package dev.hely.lib;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author ByJoako
 **/
public class Skull {

    public static ItemStack create(String value){
        boolean newVersion = Arrays.stream(Material.values()).map(Material::name).collect(Collectors.toList()).contains("PLAYER_HEAD");
        ItemStack itemStack = new ItemStack(Material.matchMaterial(newVersion ? "PLAYER_HEAD" : "SKULL_ITEM"));
        if (!newVersion) itemStack.setDurability((short) 3);
        if (value.isEmpty()) return itemStack;
        SkullMeta meta = (SkullMeta) itemStack.getItemMeta();

        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", value));

        try {
            Field field = meta.getClass().getDeclaredField("profile");
            field.setAccessible(true);
            field.set(meta, profile);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        itemStack.setItemMeta(meta);
        return  itemStack;
    }
}
