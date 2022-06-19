package com.mattmx.playermanager.storage;

import com.mattmx.playermanager.PlayerManager;
import com.mattmx.playermanager.data.PlayerData;
import org.simpleyaml.configuration.file.FileConfiguration;

import java.io.IOException;

public class YamlStorage extends StorageMethod {
    private FileConfiguration playerOptions;
    private FileConfiguration playerData;

    @Override
    public void init() {
        playerOptions = Configs.get(PlayerManager.get().getDataFolder() + "/playerOptions.yml");
        playerData = Configs.get(PlayerManager.get().getDataFolder() + "/playerData.yml");
    }

    @Override
    public boolean getHasNotify(String uuid) {
        return playerOptions.getBoolean("notify." + uuid);
    }

    @Override
    public void setHasNotify(String uuid, boolean enabled) {
        if (enabled) {
            playerOptions.set("notify." + uuid, true);
            return;
        }
        playerOptions.set("notify." + uuid, null);
    }

    @Override
    public void save() {
        try {
            playerOptions.save(PlayerManager.get().getDataFolder() + "/playerOptions.yml");
            playerData.save(PlayerManager.get().getDataFolder() + "/playerData.yml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PlayerData getPlayerData(String uuid) {
        return null;
    }

    @Override
    public String getMethod() {
        return "yaml";
    }
}
