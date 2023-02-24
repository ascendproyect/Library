package dev.hely.lib;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.enchantments.EnchantmentWrapper;
import org.bukkit.inventory.ItemStack;

/**
 * @Author Joako
 * @Date 5/1/2023 | 11:36
 **/

public class FakeGlow extends EnchantmentWrapper {
    public static Enchantment FAKE_GLOW;

    static {
        FAKE_GLOW = new FakeGlow(70);
    }

    public FakeGlow(String id) {
        super(String.valueOf(NamespacedKey.minecraft(id)));
    }

    public FakeGlow(int id) {
        super(String.valueOf(id));
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return true;
    }

    @Override
    public boolean conflictsWith(Enchantment item) {
        return false;
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return null;
    }

    @Override
    public String getName() {
        return "FakeGlow";
    }

    @Override
    public int getStartLevel() {
        return 1;
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }
}
