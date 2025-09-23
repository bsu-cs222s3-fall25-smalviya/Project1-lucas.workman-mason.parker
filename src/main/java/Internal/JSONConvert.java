package Internal;

import java.lang.reflect.Type;

import com.google.gson.Gson;

public class JSONConvert {

    public String data;

    private final Gson gson = new Gson();

    public String getGson(String jsonData) {
        this.data = jsonData;
        return String.valueOf(gson.fromJson(this.data, (Type) String.class));
    }

}
