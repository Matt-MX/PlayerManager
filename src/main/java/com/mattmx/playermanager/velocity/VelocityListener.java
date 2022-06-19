package com.mattmx.playermanager.velocity;

import com.mattmx.playermanager.PlayerManager;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.connection.DisconnectEvent;
import com.velocitypowered.api.event.connection.LoginEvent;
import com.velocitypowered.api.event.player.PlayerClientBrandEvent;
import com.velocitypowered.api.plugin.PluginManager;

public class VelocityListener {
    @Subscribe
    public void onJoin(LoginEvent e) {
        if (!e.getPlayer().getCurrentServer().isPresent()) {
            PlayerManager.get().events().playerJoinServer(e.getPlayer().getUniqueId().toString(), null);
            return;
        }
        PlayerManager.get().events().playerJoinServer(e.getPlayer().getUniqueId().toString(),
                e.getPlayer().getCurrentServer().get().getServerInfo().getName());
    }

    @Subscribe
    public void onLeave(DisconnectEvent e) {
        if (!e.getPlayer().getCurrentServer().isPresent()) {
            PlayerManager.get().events().playerLeaveServer(e.getPlayer().getUniqueId().toString(), null);
            return;
        }
        PlayerManager.get().events().playerLeaveServer(e.getPlayer().getUniqueId().toString(),
                e.getPlayer().getCurrentServer().get().getServerInfo().getName());
    }
}
