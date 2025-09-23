package Internal;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class JSONConvert {

    public String data;

    public JSONConvert(String jsonData) {
        this.data = jsonData;
        //this.data = new Data();



        Gson gson = new Gson();
        //this.data = gson.fromJson(jsonData, Data.class);

        //TODO: convert to useful data and add getters

    }

    static class Data {

    }

}
