package com.mattmx.playermanager;

import com.mattmx.playermanager.annotations.PlayerManagerPlugin;
import com.mattmx.playermanager.storage.*;
import lombok.Getter;

@PlayerManagerPlugin(
        id = "playermanager",
        name = "PlayerManager",
        version = "1.0",
        authors = {"MattMX"},
        description = "A way to manage and keep track of your playerbase!",
        url = "https://www.mattmx.com/"
)
public class PlayerManager {
    private static Environment environment;
    private static IPlayerManager instance;
    @Getter
    private static StorageManager storage;
    public static String GIT_URL = "";

    public static IPlayerManager get() {
        return instance;
    }

    public static void init() {
        Configs.init();
        StorageManager.addMethod(new YamlStorage());
        StorageManager.addMethod(new SQLiteStorage());
        StorageManager.addMethod(new MySQLStorage());
        String selectedMethod = Configs.DEFAULT.getString("storage.method");
        storage = StorageManager.get(selectedMethod);
        storage.init();
    }

    public static void stop() {
        storage.end();
    }

    public static void setInstance(IPlayerManager instance, Environment e) {
        PlayerManager.instance = instance;
        setEnvironment(e);
    }

    public static void setEnvironment(Environment e) {
        environment = e;
    }

    public enum Environment {
        VELOCITY;
    }
}
