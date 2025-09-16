package Internal;

import java.io.IOException;
import java.net.URLConnection;
import java.nio.charset.Charset;

public class JSONConvert {

    public static String convertJsonToTimestampData(URLConnection connection) {
        try {
            return readJsonAsStringFrom(connection);
        } catch(IOException e) {
            System.out.println("Unhandled IO Exception from JSON conversion.");
            return "";
        }
    }

    private static String readJsonAsStringFrom(URLConnection connection) throws IOException {
        return new String(connection.getInputStream().readAllBytes(), Charset.defaultCharset());
    }

}
