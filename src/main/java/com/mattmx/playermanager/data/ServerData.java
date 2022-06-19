package com.mattmx.playermanager.data;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ServerData {
    private String servername;
    private long uptime;
    private long uniqueJoins;
    private long totalJoins;
    private float retention;
}
