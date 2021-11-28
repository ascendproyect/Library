package dev.hely.lib.adapter;

import com.google.gson.*;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.*;
import org.bukkit.potion.PotionEffect;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class ItemStackAdapter implements JsonDeserializer<ItemStack>, JsonSerializer<ItemStack> {

    public JsonElement serialize(ItemStack source, Type type, JsonSerializationContext context) {
        return serialize(source);
    }

    public ItemStack deserialize(JsonElement source, Type type, JsonDeserializationContext context) throws JsonParseException {
        return deserialize(source);
    }

    public static JsonElement serialize(ItemStack source) {
        if (source == null) return null;

        final JsonObject object = new JsonObject();

        object.addProperty("id", source.getTypeId());
        object.addProperty(getDataKey(source), source.getDurability());
        object.addProperty("count", source.getAmount());

        if (source.hasItemMeta()) {
            final ItemMeta itemMeta = source.getItemMeta();

            if (itemMeta.hasDisplayName())
                object.addProperty("name", itemMeta.getDisplayName());

            if (itemMeta.hasLore())
                object.add("lore", listToJson(itemMeta.getLore()));

            if (itemMeta instanceof LeatherArmorMeta) {
                object.addProperty("color", ((LeatherArmorMeta) itemMeta).getColor().asRGB());
            } else if (itemMeta instanceof SkullMeta) {
                object.addProperty("skull", ((SkullMeta) itemMeta).getOwner());
            } else if (itemMeta instanceof BookMeta) {
                object.addProperty("title", ((BookMeta) itemMeta).getTitle());
                object.addProperty("author", ((BookMeta) itemMeta).getAuthor());
                object.add("pages", listToJson(((BookMeta) itemMeta).getPages()));
            } else if (itemMeta instanceof PotionMeta) {
                if (!((PotionMeta) itemMeta).getCustomEffects().isEmpty())
                    object.add("potion-effects", convertPotionEffectList(((PotionMeta) itemMeta).getCustomEffects()));
            } else if (itemMeta instanceof MapMeta) {
                object.addProperty("scaling", ((MapMeta) itemMeta).isScaling());
            } else if (itemMeta instanceof EnchantmentStorageMeta) {
                final JsonObject storedEnchantments = new JsonObject();

                for (Map.Entry<Enchantment, Integer> entry : ((EnchantmentStorageMeta) itemMeta).getStoredEnchants().entrySet()) {
                    storedEnchantments.addProperty(entry.getKey().getName(), entry.getValue());
                }

                object.add("stored-enchants", storedEnchantments);
            }
        }

        if (source.getEnchantments().size() != 0) {
            final JsonObject enchantments = new JsonObject();

            for (Map.Entry<Enchantment, Integer> enchants : source.getEnchantments().entrySet()) {
                enchantments.addProperty(enchants.getKey().getName(), enchants.getValue());
            }

            object.add("enchants", enchantments);
        }

        return object;
    }

    public static ItemStack deserialize(JsonElement source) {
        if (source == null) return null;
        if (!source.isJsonObject()) return null;

        final JsonObject element = source.getAsJsonObject();

        final int id = element.get("id").getAsInt();
        final short data = element.has("damage") ? element.get("damage").getAsShort() : (element.has("data") ? element.get("data").getAsShort() : 0);
        final int count = element.get("count").getAsInt();

        final ItemStack itemStack = new ItemStack(id, count, data);
        final ItemMeta itemMeta = itemStack.getItemMeta();

        if (element.has("name"))
            itemMeta.setDisplayName(element.get("name").getAsString());

        if (element.has("lore"))
            itemMeta.setLore(jsonToList(element.get("lore")));

        if (element.has("color")) {
            ((LeatherArmorMeta) itemMeta).setColor(Color.fromRGB(element.get("color").getAsInt()));
        } else if (element.has("skull")) {
            ((SkullMeta) itemMeta).setOwner(element.get("skull").getAsString());
        } else if (element.has("title")) {
            ((BookMeta) itemMeta).setTitle(element.get("title").getAsString());
            ((BookMeta) itemMeta).setAuthor(element.get("author").getAsString());
            ((BookMeta) itemMeta).setPages(jsonToList(element.get("pages")));
        } else if (element.has("potion-effects")) {
            final PotionMeta potionMeta = (PotionMeta) itemMeta;

            for (PotionEffect effect : convertPotionEffectList(element.get("potion-effects"))) {
                potionMeta.addCustomEffect(effect, false);
            }
        } else if (element.has("scaling")) {
            ((MapMeta) itemMeta).setScaling(element.get("scaling").getAsBoolean());
        } else if (element.has("stored-enchants")) {
            final JsonObject enchantments = (JsonObject) element.get("stored-enchants");

            for (Enchantment enchantment : Enchantment.values()) {
                if (enchantments.has(enchantment.getName())) {
                    ((EnchantmentStorageMeta) itemMeta).addStoredEnchant(enchantment, enchantments.get(enchantment.getName()).getAsInt(), true);
                }
            }
        }

        itemStack.setItemMeta(itemMeta);

        if (element.has("enchants")) {
            final JsonObject enchantments = (JsonObject) element.get("enchants");

            for (Enchantment enchantment : Enchantment.values()) {
                if (enchantments.has(enchantment.getName())) {
                    itemStack.addUnsafeEnchantment(enchantment, enchantments.get(enchantment.getName()).getAsInt());
                }
            }
        }

        return itemStack;
    }

    private static String getDataKey(ItemStack item) {
        if (item.getType() == Material.AIR) {
            return "data";
        }

        if (Enchantment.DURABILITY.canEnchantItem(item)) {
            return "damage";
        }

        return "data";
    }

    public static JsonArray listToJson(Collection<String> strings) {
        final JsonArray array = new JsonArray();

        for (String string : strings) {
            array.add(new JsonPrimitive(string));
        }

        return array;
    }

    public static List<String> jsonToList(JsonElement jsonElement) {
        if (jsonElement == null) return null;
        if (!jsonElement.isJsonArray()) return null;

        final List<String> list = new ArrayList<>();
        final JsonArray array = jsonElement.getAsJsonArray();

        for (JsonElement element : array) {
            list.add(element.getAsString());
        }

        return list;
    }

    public static JsonArray convertPotionEffectList(Collection<PotionEffect> potionEffects) {
        final JsonArray array = new JsonArray();

        for (PotionEffect e : potionEffects) {
            array.add(PotionEffectAdapter.toJson(e));
        }

        return array;
    }

    public static List<PotionEffect> convertPotionEffectList(JsonElement jsonElement) {
        if (jsonElement == null) return null;
        if (!jsonElement.isJsonArray()) return null;

        final JsonArray array = jsonElement.getAsJsonArray();
        final List<PotionEffect> list = new ArrayList<>();

        for (JsonElement anArray : array) {
            final PotionEffect effect = PotionEffectAdapter.fromJson(anArray);

            if (effect == null) continue;

            list.add(effect);
        }

        return list;
    }
}
