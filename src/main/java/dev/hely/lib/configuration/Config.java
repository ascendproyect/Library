package dev.hely.lib.configuration;

/**
 * @author Leandro Figueroa (LeandroSSJ)
 * domingo, marzo 28, 2021
 */

import dev.hely.lib.CC;
import lombok.Getter;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
public class Config {

    private final File file;
    private YamlConfiguration configuration;

    public Config(JavaPlugin plugin, String name) throws IOException, InvalidConfigurationException {
        file = new File(plugin.getDataFolder(), name + ".yml");
        if (!file.exists()) {
            plugin.saveResource(name + ".yml", false);
        }

        this.configuration = YamlConfiguration.loadConfiguration(this.getFile());
    }

    public void save() {
        try {
            this.configuration.save(this.file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        this.configuration = YamlConfiguration.loadConfiguration(this.getFile());
    }

    public double getDouble(String path) {
        if (this.configuration.contains(path)) return this.configuration.getDouble(path);
        return 0.0D;
    }

    public int getInt(String path) {
        if (this.configuration.contains(path)) return this.configuration.getInt(path);
        return 0;
    }

    public boolean getBoolean(String path) {
        if (this.configuration.contains(path)) return this.configuration.getBoolean(path);
        return true;
    }

    public String getString(String path) {
        if (this.configuration.contains(path)) return this.configuration.getString(CC.translate(path));
        return CC.translate("&cString not found: '%path%'").replace("%path%", path);
    }

    public List<String> getStringList(String path) {
        if (this.configuration.contains(path)) {
            ArrayList<String> arrayList = new ArrayList<>();
            for (String string : this.configuration.getStringList(path))
                arrayList.add(CC.translate(string));
            return arrayList;
        }
        return Collections.singletonList(CC.translate("&cString not found: '%path%'").replace("%path%", path));
    }

    public File getFile() {
        return this.file;
    }

    public YamlConfiguration getConfig() {
        return this.configuration;
    }
}