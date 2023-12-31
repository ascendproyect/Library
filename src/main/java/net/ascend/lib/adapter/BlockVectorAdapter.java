package net.ascend.lib.adapter;

import com.google.gson.*;
import org.bukkit.util.BlockVector;

import java.lang.reflect.Type;

public class BlockVectorAdapter implements JsonDeserializer<BlockVector>, JsonSerializer<BlockVector> {

    public BlockVector deserialize(JsonElement source, Type type, JsonDeserializationContext context) throws JsonParseException {
        return fromJson(source);
    }

    public JsonElement serialize(BlockVector source, Type type, JsonSerializationContext context) {
        return toJson(source);
    }

    public static JsonObject toJson(BlockVector source) {
        if (source == null) return null;

        final JsonObject object = new JsonObject();

        object.addProperty("x", source.getX());
        object.addProperty("y", source.getY());
        object.addProperty("z", source.getZ());

        return object;
    }

    public static BlockVector fromJson(JsonElement source) {
        if (source == null) return null;
        if (!source.isJsonObject()) return null;

        final JsonObject object = source.getAsJsonObject();

        double x = object.get("x").getAsDouble();
        double y = object.get("y").getAsDouble();
        double z = object.get("z").getAsDouble();

        return new BlockVector(x, y, z);
    }
}
