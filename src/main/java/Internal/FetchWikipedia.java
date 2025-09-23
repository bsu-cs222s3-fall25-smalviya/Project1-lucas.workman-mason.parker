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

    public FetchWikipedia(String subject) throws NoSuchURLException, BadConnectionException, CouldNotConvertToStringException {
        URLConnection connection = getRawWikipediaData(subject);
        String jsonData = connectionAsString(connection);
        this.conversion = new JSONConvert(jsonData);
    }

    public JSONConvert getConversion() {
        return this.conversion;
    }

    private static URLConnection getRawWikipediaData(String subject) throws NoSuchURLException, BadConnectionException {
        String encodedURL = "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                URLEncoder.encode(subject, Charset.defaultCharset()) + "&rvprop=timestamp" +
                URLEncoder.encode("|", Charset.defaultCharset()) + "user&rvlimit=4&redirects";

        return getUrlConnection(encodedURL);
    }

    private static URLConnection getUrlConnection(String encodedURL) throws NoSuchURLException, BadConnectionException {
        URI uri;
        try {
            uri = new URI(encodedURL);
        } catch (URISyntaxException e) {
            throw new NoSuchURLException();
        }

        URLConnection connection;
        try {
            connection = uri.toURL().openConnection();
            connection.setRequestProperty("User-Agent", "FirstProject/0.1 (academic use; mason.parker@bsu.edu/lucas.workman@bsu.edu)");
            connection.connect();
        } catch (IOException e) {
            throw new BadConnectionException();
        }
        return connection;
    }

    private static String connectionAsString(URLConnection connection) throws CouldNotConvertToStringException {
        try {
            return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
        } catch (IOException e) {
            throw new CouldNotConvertToStringException();
        }
    }

    public static class NoSuchURLException extends Exception {}

    public static class BadConnectionException extends Exception {}

    public static class CouldNotConvertToStringException extends Exception {}

}
