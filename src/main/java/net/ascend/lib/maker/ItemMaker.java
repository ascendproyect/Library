package net.ascend.lib.maker;

import com.cryptomorin.xseries.XMaterial;
import com.google.common.collect.Lists;
import net.ascend.lib.CC;
import net.ascend.lib.util.FakeGlow;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static net.ascend.lib.CC.translate;

public class ItemMaker {

    public static ItemMaker of(Material material) {
        if (Bukkit.getVersion().contains("1.7")) {
            return new ItemMaker(material, 1, (short) 0);
        } else {
            return new ItemMaker(XMaterial.matchXMaterial(material).parseMaterial(), 1, (short) 0);
        }
    }

    public static ItemMaker of(Material material, int amount) {
        if (Bukkit.getVersion().contains("1.7")) {
            return new ItemMaker(material, amount, (short) 0);
        } else {
            return new ItemMaker(XMaterial.matchXMaterial(material).parseMaterial(), amount, (short) 0);
        }
    }

    public static ItemMaker of(Material material, int amount, int data) {
        if (Bukkit.getVersion().contains("1.7")) {
            return new ItemMaker(material, amount, (short) data);
        } else {
            return new ItemMaker(XMaterial.matchXMaterial(material).parseMaterial(), amount, (short) data);
        }
    }

    public static ItemMaker copyOf(ItemStack itemStack) {
        return new ItemMaker(itemStack);
    }

    private final ItemStack itemStack;

    private ItemMaker(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    private ItemMaker(Material material, int amount, short data) {
        if (Bukkit.getVersion().contains("1.7")) {
            this.itemStack = new ItemStack(material, amount, data);
        } else {
            this.itemStack = new ItemStack(XMaterial.matchXMaterial(material).parseMaterial(), amount, data);
        }
    }

    public ItemMaker setAmount(int amount) {
        itemStack.setAmount(amount);
        return this;
    }

    public ItemMaker setData(short data) {
        itemStack.setDurability(data);
        return this;
    }

    public ItemMaker setDisplayName(String name) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(translate(name));

        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemMaker setLore(String... lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(Arrays.stream(lore).map(CC::translate).collect(Collectors.toList()));

        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemMaker addLore(String lores) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        List<String> lore = itemMeta.getLore();
        if (lore == null) {
            lore = Lists.newArrayList();
        }
        lore.add(CC.translate(lores));

        itemMeta.setLore(lore);

        itemStack.setItemMeta(itemMeta);
        return this;
    }


    public ItemMaker setLore(List<String> lore) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(lore.stream().map(CC::translate).collect(Collectors.toList()));

        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemMaker setOwner(String owner) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta instanceof SkullMeta) {
            SkullMeta skullMeta = (SkullMeta) itemMeta;
            skullMeta.setOwner(owner);

            itemStack.setItemMeta(itemMeta);
        }

        return this;
    }

    public ItemMaker setColor(Color color) {
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (itemMeta instanceof LeatherArmorMeta) {
            LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) itemMeta;
            leatherArmorMeta.setColor(color);

            itemStack.setItemMeta(itemMeta);
        }

        return this;
    }

    public ItemMaker setFlags(ItemFlag... flags) {
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.addItemFlags(flags);

        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemMaker addFakeGlow() {
        ItemMeta itemMeta = itemStack.getItemMeta();

        if (Bukkit.getVersion().contains("1.7") || Bukkit.getVersion().contains("1.8")) {
            Enchantment FAKE_GLOW = new FakeGlow(70);;
            itemMeta.addEnchant(FAKE_GLOW, 2, true);
        } else {
            itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            addEnchant(Enchantment.ARROW_INFINITE, 1);
        }

        itemStack.setItemMeta(itemMeta);
        return this;
    }

    public ItemMaker addEnchant(Enchantment enchantment, int level) {
        itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    public ItemMaker removeEnchantment(Enchantment enchantment) {
        itemStack.removeEnchantment(enchantment);
        return this;
    }

    public ItemStack build() {
        return itemStack.clone();
    }
}
