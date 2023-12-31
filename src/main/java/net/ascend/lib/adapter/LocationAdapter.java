package net.ascend.lib.adapter;

import com.google.gson.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.lang.reflect.Type;

public class LocationAdapter implements JsonDeserializer<Location>, JsonSerializer<Location> {

    public JsonElement serialize(Location source, Type type, JsonSerializationContext context) {
        return toJson(source);
    }

    public Location deserialize(JsonElement source, Type type, JsonDeserializationContext context) throws JsonParseException {
        return fromJson(source);
    }

    public static JsonObject toJson(Location source) {
        if (source == null) return null;

        final JsonObject object = new JsonObject();

        object.addProperty("world", source.getWorld().getName());

        object.addProperty("x", source.getX());
        object.addProperty("y", source.getY());
        object.addProperty("z", source.getZ());

        object.addProperty("yaw", source.getYaw());
        object.addProperty("pitch", source.getPitch());

        return object;
    }

    public static Location fromJson(JsonElement source) {
        if (source == null) return null;
        if (!source.isJsonObject()) return null;

        final JsonObject object = source.getAsJsonObject();

        final World world = Bukkit.getWorld(object.get("world").getAsString());

        final double x = object.get("x").getAsDouble();
        final double y = object.get("y").getAsDouble();
        final double z = object.get("z").getAsDouble();

        final float yaw = object.get("yaw").getAsFloat();
        final float pitch = object.get("pitch").getAsFloat();

        return new Location(world, x, y, z, yaw, pitch);
    }
}
