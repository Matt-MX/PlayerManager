package com.mattmx.playermanager.storage;

import com.mattmx.playermanager.PlayerManager;

import java.util.ArrayList;
import java.util.List;

public class StorageManager {
    private static List<StorageMethod> methods = new ArrayList<>();
    private StorageMethod method;

    public void init() {
        method.init();
    }

    public void end() {
        method.save();
    }

    public StorageMethod get() {
        return this.method;
    }

    public void setMethod(StorageMethod method) {
        this.method = method;
    }

    public static void addMethod(StorageMethod method) {
        methods.add(method);
    }

    public static StorageManager get(String methodName) {
        StorageManager manager = new StorageManager();
        for (StorageMethod method : methods) {
            if (method.getMethod().equalsIgnoreCase(methodName)) {
                PlayerManager.get().logger().info("Using '" + methodName + "' as storage method!");
                manager.setMethod(method);
                break;
            }
        }
        if (manager.method == null) {
            PlayerManager.get().logger().info("Couldn't find a storage method to use! Using default: 'yaml'");
            manager.setMethod(new YamlStorage());
        }
        manager.init();
        return manager;
    }
}
