package com.mattmx.playermanager.storage;

import com.mattmx.playermanager.PlayerManager;
import org.simpleyaml.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Configs {
    public static YamlConfiguration DEFAULT;
    public static String DEFAULT_PATH = PlayerManager.get().getDataFolder() + "/config.yml";

    public static void init() {
        DEFAULT = get(DEFAULT_PATH, "config.yml");
    }

    public static void save(YamlConfiguration config, String dest) {
        try {
            config.save(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static YamlConfiguration get(String path) {
        return get(path, null);
    }

    public static YamlConfiguration get(String path, String def) {
        File file = new File(path);
        if (!file.exists()) {
            try {
                if (def != null) {
                    PlayerManager.get().saveResource(def, false);
                } else {
                    file.createNewFile();
                }
                PlayerManager.get().logger().info("Created " + path);
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        return YamlConfiguration.loadConfiguration(file);
    }
}