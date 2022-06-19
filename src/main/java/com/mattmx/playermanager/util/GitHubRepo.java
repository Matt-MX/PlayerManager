package com.mattmx.playermanager.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GitHubRepo {
    public static boolean isOutDated(String latestVersion, String currentVersion) {
        return latestVersion != currentVersion;
    }

    public static boolean validRepo(String json) {
        JsonObject object = JsonParser.parseString(json).getAsJsonObject();
        return object.get("message").getAsString() != null;
    }

    public static String getLatestDownload(String json) {
        JsonObject object = JsonParser.parseString(json).getAsJsonObject();
        return object.get("html_url").getAsString();
    }

    public static String getLatestVersion(String json) {
        JsonObject object = JsonParser.parseString(json).getAsJsonObject();
        return object.get("tag_name").getAsString();
    }
}
