package com.mattmx.playermanager;

public interface IPlayerManagerEvents {
    default void playerJoinServer(String uuid, String server) {
    }
    default void playerLeaveServer(String uuid, String server) {
    }
}
