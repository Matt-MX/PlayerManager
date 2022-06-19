package com.mattmx.playermanager.velocity;

import com.google.inject.Inject;
import com.mattmx.playermanager.IPlayerManager;
import com.mattmx.playermanager.IPlayerManagerEvents;
import com.mattmx.playermanager.PlayerManager;
import com.mattmx.playermanager.annotations.PlayerManagerPlugin;
import com.mattmx.playermanager.util.Fetch;
import com.mattmx.playermanager.util.GitHubRepo;
import com.mattmx.playermanager.velocity.VelocityPlugin;
import com.mattmx.playermanager.velocity.commands.PlayerManagerCommand;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ProxyServer;
import org.slf4j.Logger;

import java.net.InetSocketAddress;

@Plugin(
        id = "playermanager",
        name = "PlayerManager",
        version = "1.0",
        description = "Manage and track your players with ease.",
        url = "https://www.mattmx.com/",
        authors = {"MattMX"}
)
public class PlayerManagerVelocity extends VelocityPlugin implements IPlayerManager {
    private final VelocityEvents events = new VelocityEvents();

    @Inject
    public PlayerManagerVelocity(ProxyServer server, Logger logger) {
        init(server, logger, this.getClass().getAnnotation(Plugin.class).id());
        PlayerManager.setInstance(this, PlayerManager.Environment.VELOCITY);
        new Fetch<String>(PlayerManager.GIT_URL).then((response) -> {
            if (GitHubRepo.validRepo(response)) {
                String latest = GitHubRepo.getLatestVersion(response);
                if (GitHubRepo.isOutDated(latest, PlayerManager.class.getAnnotation(PlayerManagerPlugin.class).version())) {
                    logger.warn("PlayerManager is outdated! Newest version: v" + latest);
                    logger.warn("Download it here: " + GitHubRepo.getLatestDownload(response));
                } else {
                    logger.warn("Running latest, v" + latest);
                }
            } else {
                logger.warn("Github Repository unreachable or invalid.");
            }
        }).fail((e) -> {
            logger.warn("Unable to check for latest version!");
        }).get();
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {
        PlayerManager.init();
        server().getEventManager().register(this, new VelocityListener());
        server().getCommandManager().register("playermanagervelocity", new PlayerManagerCommand(), "pmv");
    }

    @Override
    public ProxyServer server() {
        return super.getServer();
    }

    @Override
    public IPlayerManagerEvents events() {
        return events;
    }

    @Override
    public InetSocketAddress address() {
        return server().getBoundAddress();
    }

    @Override
    public Logger logger() {
        return super.logger();
    }
}
