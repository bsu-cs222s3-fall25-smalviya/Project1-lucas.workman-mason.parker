package Internal;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class TestCases {

    @Test
    void Test() throws IOException, URISyntaxException {
        FetchWikipedia wikipediaFetch = new FetchWikipedia("Hello World");
        System.out.println(wikipediaFetch.getConversion().data);
    }
}
