package com.mattmx.playermanager.storage;

import com.mattmx.playermanager.data.PlayerData;

public class MySQLStorage extends StorageMethod{
    @Override
    public void init() {

    }

    @Override
    public boolean getHasNotify(String uuid) {
        return false;
    }

    @Override
    public void setHasNotify(String uuid, boolean enabled) {

    }

    @Override
    public PlayerData getPlayerData(String uuid) {
        return null;
    }

    @Override
    public String getMethod() {
        return "mysql";
    }
}
