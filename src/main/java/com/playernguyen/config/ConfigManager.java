package com.playernguyen.config;

import com.playernguyen.DayNightPVP;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ConfigManager {

    private HashMap<ConfigKey, Object> key;

    private File file;
    private YamlConfiguration configuration;

    public ConfigManager () {
        file = new File(DayNightPVP.getInstance().getDataFolder(), "config.yml");
        configuration = YamlConfiguration.loadConfiguration(file);
        try {
            this.loadDefault();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadDefault() throws IOException {
        if (!file.exists()) {
            getConfiguration().options().copyDefaults(true);
            for (ConfigKey value : ConfigKey.values()) {
                configuration.addDefault(value.getPath(), value.getDefaultSetting());
            }
            getConfiguration().save(getFile());
        }
    }

    private Object get(ConfigKey key) {
        return getConfiguration().get(key.getPath());
    }

    public void load () {
        key = new HashMap<>();
        for (ConfigKey value : ConfigKey.values()) {
            key.put(value, get(value));
        }
    }

    public File getFile() {
        return file;
    }

    public YamlConfiguration getConfiguration() {
        return configuration;
    }

    public Object getConfig (ConfigKey key) {
        return this.key.get(key);
    }

    public List<String> getListString (ConfigKey key) {
        return (List<String>) get(key);
    }

    public long getLong(ConfigKey key) {
        return Long.valueOf(String.valueOf(get(key)));
    }

    public boolean getBool (ConfigKey key) {
        return (boolean) get(key);
    }

    public String getString (ConfigKey key) {
        return (String) get(key);
    }

    public HashMap<ConfigKey, Object> getMap() {
        return key;
    }
}
