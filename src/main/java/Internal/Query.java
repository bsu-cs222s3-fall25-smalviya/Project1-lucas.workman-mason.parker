package Internal;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;
import java.net.http.*;
import java.net.*;

public class Query {

    public static void main(String[] args) throws IOException, URISyntaxException {
        URLConnection connection = connectToWikipedia();
        String jsonData = readJsonAsStringFrom(connection);
        System.out.println(jsonData);
    }

    public static URLConnection connectToWikipedia() throws IOException, URISyntaxException {

        String encodedURL = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                URLEncoder.encode("Trump", Charset.defaultCharset()) + "&rvprop=timestamp" +
                URLEncoder.encode("|", Charset.defaultCharset()) + "user&rvlimit=4&redirects";
        URI uri = new URI(encodedURL);

        URLConnection connection = uri.toURL().openConnection();
        connection.setRequestProperty("User-Agent", "FirstProject/0.1 (academic use; https://example.com)");
        connection.connect();
        return connection;
    }

    private static String readJsonAsStringFrom(URLConnection urlConnection) throws IOException {
        return new String(urlConnection.getInputStream().readAllBytes(), Charset.defaultCharset());
    }


}
