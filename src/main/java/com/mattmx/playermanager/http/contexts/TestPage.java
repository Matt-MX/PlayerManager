package com.mattmx.playermanager.http.contexts;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class TestPage implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "<h1>HTTP server is running.</h1><p>Lets go!</p>";
        exchange.sendResponseHeaders(200, response.length());
        OutputStream stream = exchange.getResponseBody();
        stream.write(response.getBytes());
        stream.close();
    }
}
