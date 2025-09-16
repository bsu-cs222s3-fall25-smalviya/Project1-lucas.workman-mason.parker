package Internal;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLConnection;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        URLConnection connection = FetchWikipedia.getRawWikipediaData("George Washington");
        String jsonData = JSONConvert.convertJsonToTimestampData(connection);
        System.out.println(jsonData);
    }
}