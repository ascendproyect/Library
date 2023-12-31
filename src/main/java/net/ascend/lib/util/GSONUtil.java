package net.ascend.lib.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.ascend.lib.adapter.*;
import lombok.experimental.UtilityClass;
import org.bukkit.Location;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.BlockVector;
import org.bukkit.util.Vector;

/**
 * @author Maykol Morales Morante (zSirSpectro)
 * Wednesday, July 14, 2021
 */

@UtilityClass
public class GSONUtil {

    public static final Gson FANCY = new GsonBuilder().registerTypeHierarchyAdapter(PotionEffect.class, new PotionEffectAdapter()).registerTypeHierarchyAdapter(Location.class, new LocationAdapter()).registerTypeHierarchyAdapter(Vector.class, new VectorAdapter()).registerTypeAdapter(BlockVector.class, new BlockVectorAdapter()).setPrettyPrinting().serializeNulls().create();
    public static final Gson PLAIN = new GsonBuilder().registerTypeHierarchyAdapter(PotionEffect.class, new PotionEffectAdapter()).registerTypeHierarchyAdapter(Location.class, new LocationAdapter()).registerTypeHierarchyAdapter(Vector.class, new VectorAdapter()).registerTypeAdapter(BlockVector.class, new BlockVectorAdapter()).serializeNulls().create();

}
