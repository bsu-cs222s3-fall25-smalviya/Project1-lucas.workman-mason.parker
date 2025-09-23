package Internal;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.*;

public class FetchWikipedia {

    private final JSONConvert conversion;



    public FetchWikipedia() throws IOException, URISyntaxException {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Wikipedia Name: ");
        String subject = input.nextLine();
        URLConnection connection = getRawWikipediaData(subject);
        String jsonData = connectionAsString(connection);
        this.conversion = new JSONConvert(jsonData);
    }

    public JSONConvert getConversion() {
        return this.conversion;
    }


    private static URLConnection getRawWikipediaData(String subject) throws IOException, URISyntaxException {

        String encodedURL = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                URLEncoder.encode(subject, Charset.defaultCharset()) + "&rvprop=timestamp" +
                URLEncoder.encode("|", Charset.defaultCharset()) + "user&rvlimit=4&redirects";
        URI uri = new URI(encodedURL);

        URLConnection connection = uri.toURL().openConnection();
        connection.setRequestProperty("User-Agent", "FirstProject/0.1 (academic use; https://example.com)");
        connection.connect();
        return connection;
    }

    private static String connectionAsString(URLConnection connection) throws IOException {
        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
    }



}
