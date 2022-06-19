package com.mattmx.playermanager;

import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

import java.io.File;

public interface IPlayerManager {
    default Logger logger() {
        return null;
    }
    default ProxyServer server() {
        return null;
    }
    File getDataFolder();
    void saveResource(String f, boolean b);
    IPlayerManagerEvents events();
}
