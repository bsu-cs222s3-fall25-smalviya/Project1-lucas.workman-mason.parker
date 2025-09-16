package Internal;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.lang.Object;
import java.net.URLConnection;

public class Query {

    public static void main(String[] args) throws IOException
    {
        URL url = new URL("https://en.wikipedia.org");
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "Revision Reporter/0.1 (me@bsu.edu)");
        InputStream inputStream = connection.getInputStream();
        System.out.println(inputStream);
    }
}
