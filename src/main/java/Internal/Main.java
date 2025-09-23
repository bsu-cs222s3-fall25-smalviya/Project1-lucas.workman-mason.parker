package Internal;

import java.io.IOException;
import java.net.URISyntaxException;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {
        FetchWikipedia wikipediaFetch = new FetchWikipedia();
        System.out.println(wikipediaFetch.getConversion().data.title);
    }
}