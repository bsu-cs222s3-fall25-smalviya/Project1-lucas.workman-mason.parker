package Internal;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.annotations.SerializedName;

import java.util.*;

public class JSONConvert {

    public Data data;

    public JSONConvert(String jsonData) {
        this.data = new Data();



        Gson gson = new Gson();

        JsonElement jsonElement = new JsonParser().parse(jsonData);
        JsonElement pages = jsonElement.getAsJsonObject().get("query").getAsJsonObject().get("pages");

        Set<Map.Entry<String, JsonElement>> entrySet = pages.getAsJsonObject().entrySet();

        JsonElement yourDesiredElement = null;

        for(Map.Entry<String,JsonElement> entry : entrySet){
            yourDesiredElement = entry.getValue();
        }

        this.data = gson.fromJson(yourDesiredElement.toString(), Data.class);

        //TODO: convert to useful data and add getters

    }

    static class Revision {
        String user;
        String anon;
        String timestamp;
    }

    static class Data {
        @SerializedName("pageid")
        int id;
        int ns;
        String title;
        ArrayList<Revision> revisions;
    }

}
