package net.ascend.lib.adapter;

import com.google.gson.*;
import org.bukkit.util.Vector;

import java.lang.reflect.Type;

public class VectorAdapter implements JsonDeserializer<Vector>, JsonSerializer<Vector> {

    public Vector deserialize(JsonElement source, Type type, JsonDeserializationContext context) throws JsonParseException {
        return fromJson(source);
    }

    public JsonElement serialize(Vector source, Type type, JsonSerializationContext context) {
        return toJson(source);
    }

    public static JsonObject toJson(Vector source) {
        if (source == null) return null;

        final JsonObject object = new JsonObject();

        object.addProperty("x", source.getX());
        object.addProperty("y", source.getY());
        object.addProperty("z", source.getZ());

        return object;
    }

    public static Vector fromJson(JsonElement source) {
        if (source == null) return null;
        if (!source.isJsonObject()) return null;

        final JsonObject object = source.getAsJsonObject();

        final double x = object.get("x").getAsDouble();
        final double y = object.get("y").getAsDouble();
        final double z = object.get("z").getAsDouble();

        return new Vector(x, y, z);
    }
}
