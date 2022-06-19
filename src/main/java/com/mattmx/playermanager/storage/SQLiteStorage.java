package com.mattmx.playermanager.storage;

import com.mattmx.playermanager.PlayerManager;
import com.mattmx.playermanager.data.PlayerData;
import com.velocitypowered.api.plugin.PluginManager;
import org.simpleyaml.configuration.file.FileConfiguration;

import java.sql.*;

public class SQLiteStorage extends StorageMethod {
    private Statement statement;

    @Override
    public void init() {
        try {
            FileConfiguration config = Configs.DEFAULT;
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection(
                    "jdbc:sqlite:" + PlayerManager.get().getDataFolder() + "/"
                            + config.getString("storage.data.database", "playerManager.db")
            );
            statement = conn.createStatement();
            statement.setQueryTimeout(0);
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS options_notify(" +
                    "uuid TEXT," +
                    "enabled BOOLEAN," +
                    "PRIMARY KEY(uuid))");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean getHasNotify(String uuid) {
        try {
            ResultSet rs = statement.executeQuery("SELECT enabled FROM options_notify WHERE uuid = '" + uuid + "'");
            if (rs.next()) {
                return rs.getBoolean("enabled");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void setHasNotify(String uuid, boolean enabled) {
        try {
            statement.executeUpdate(
                    "INSERT OR IGNORE INTO options_notify VALUES ('" + uuid + "', " + enabled + ");" +
                            "UPDATE reconnect_data SET enabled = " + enabled + " where uuid ='" + uuid + "'"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void save() {
        try {
            statement.closeOnCompletion();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PlayerData getPlayerData(String uuid) {
        return null;
    }

    @Override
    public String getMethod() {
        return "sqlite";
    }
}
