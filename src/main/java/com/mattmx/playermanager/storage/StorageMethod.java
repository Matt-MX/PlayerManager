package com.mattmx.playermanager.storage;

import com.mattmx.playermanager.PlayerManager;
import com.mattmx.playermanager.data.PlayerData;
import com.mattmx.playermanager.data.ServerData;

import java.util.ArrayList;
import java.util.List;

public abstract class StorageMethod {
    public abstract void init();
    public abstract boolean getHasNotify(String uuid);
    public abstract void setHasNotify(String uuid, boolean enabled);
    public abstract PlayerData getPlayerData(String uuid);
    public ServerData getServerData(String servername) {
        return null;
    }
    public abstract String getMethod();
    public void save() {

    }
}
