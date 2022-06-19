package com.mattmx.playermanager.http;

import com.mattmx.playermanager.PlayerManager;
import com.mattmx.playermanager.http.contexts.TestPage;
import com.sun.net.httpserver.HttpServer;
import lombok.Getter;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebServer {
    @Getter
    private static HttpServer server;

    public static void start(int port) {
        if (server != null) return;
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);
            server.createContext("/test/?", new TestPage());
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
