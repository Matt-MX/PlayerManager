package com.mattmx.playermanager.data;

import lombok.Builder;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;

@Builder
@Getter
public class PlayerData {
    private HashMap<String, Long> activity;
    private long time;
    private List<String> ips;
    private List<String> clients;
}
