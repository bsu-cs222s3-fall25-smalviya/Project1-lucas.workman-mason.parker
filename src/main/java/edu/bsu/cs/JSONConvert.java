package edu.bsu.cs;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import java.util.*;

public class JSONConvert {

    private Data data;

    public JSONConvert(String jsonData) throws FetchWikipedia.NoSuchURLException {
        this.data = new Data();

        Gson gson = new Gson();

        JsonElement jsonElement = JsonParser.parseString(jsonData);
        JsonElement pages = jsonElement.getAsJsonObject().get("query").getAsJsonObject().get("pages");

        Set<Map.Entry<String, JsonElement>> entrySet = pages.getAsJsonObject().entrySet();

        Optional<Map.Entry<String, JsonElement>> first = entrySet.stream().findFirst();

        if (first.isEmpty()) {
            throw new FetchWikipedia.NoSuchURLException();
        }

        this.data = gson.fromJson(first.get().getValue().toString(), Data.class);

        // If pageID is 0, that means there is no such page
        if (this.data.id == 0) {
            throw new FetchWikipedia.NoSuchURLException();
        }
    }

    public Data getData() {
        return data;
    }

    public static class Revision {
        String user;
        String timestamp;
    }

    public static class Data {
        @SerializedName("pageid")
        int id;
        String title;
        List<Revision> revisions;
    }
}
