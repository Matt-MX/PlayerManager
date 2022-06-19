package com.mattmx.playermanager.velocity;

import com.mattmx.playermanager.PlayerManager;
import com.mattmx.playermanager.util.Fetch;
import com.velocitypowered.api.scheduler.ScheduledTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class VelocityFetch extends Fetch {

    public VelocityFetch(String url) {
        super(url);
    }

    public VelocityFetch(Callable task) {
        super(task);
    }

    public ScheduledTask velocityTask() {
        PlayerManagerVelocity pl = (PlayerManagerVelocity) PlayerManager.get();
        ScheduledTask task = pl.server().getScheduler().buildTask(pl, this::execute).schedule();
        return task;
    }
}
