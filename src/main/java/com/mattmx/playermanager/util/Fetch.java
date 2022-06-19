package com.mattmx.playermanager.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class Fetch<T> {
    private Callable<T> task;
    private Consumer<Exception> fail;
    private Consumer<T> then;
    private URL url;

    public Fetch(String url) {
        try {
            this.url = new URL(url);
            this.task = () -> (T)getPage(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public Fetch(Callable<T> task) {
        this.task = task;
    }

    public Fetch<T> then(Consumer<T> task) {
        then = task;
        return this;
    }

    public Fetch<T> fail(Consumer<Exception> task) {
        fail = task;
        return this;
    }

    public Thread get() {
        Thread thread = new Thread(this::execute);
        thread.start();
        return thread;
    }

    protected void execute() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<T> result = executor.submit(task);
        while (!result.isDone()) {
        }
        try {
            T actualResult = result.get();
            if (then != null) then.accept(actualResult);
        } catch (InterruptedException | ExecutionException e) {
            if (fail != null) fail.accept(e);
        }
    }

    public static String getPage(String url) {
        try {
            URLConnection connection = new URL(url).openConnection();
            connection.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
