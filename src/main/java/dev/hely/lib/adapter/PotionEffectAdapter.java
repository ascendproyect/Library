package dev.hely.lib.adapter;

import com.google.gson.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.lang.reflect.Type;

public class PotionEffectAdapter implements JsonDeserializer<PotionEffect>, JsonSerializer<PotionEffect> {

    public JsonElement serialize(PotionEffect source, Type type, JsonSerializationContext context) {
        return toJson(source);
    }

    public PotionEffect deserialize(JsonElement source, Type type, JsonDeserializationContext context) throws JsonParseException {
        return fromJson(source);
    }

    public static JsonObject toJson(PotionEffect source) {
        if (source == null) return null;

        final JsonObject object = new JsonObject();

        object.addProperty("id", source.getType().getId());
        object.addProperty("duration", source.getDuration());
        object.addProperty("amplifier", source.getAmplifier());
        object.addProperty("ambient", source.isAmbient());

        return object;
    }

    public static PotionEffect fromJson(JsonElement source) {
        if (source == null) return null;
        if (!source.isJsonObject()) return null;

        final JsonObject object = source.getAsJsonObject();

        final PotionEffectType effectType = PotionEffectType.getById(object.get("id").getAsInt());

        final int duration = object.get("duration").getAsInt();
        final int amplifier = object.get("amplifier").getAsInt();

        final boolean ambient = object.get("ambient").getAsBoolean();

        return new PotionEffect(effectType, duration, amplifier, ambient);
    }
}
