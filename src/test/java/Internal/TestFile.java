package Internal;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class TestFile {

    private static final Scanner input = new Scanner(System.in);
    @Test
    public void ScannerTestCase() throws FetchWikipedia.NoSuchURLException, FetchWikipedia.CouldNotConvertToStringException, FetchWikipedia.BadConnectionException {
        System.out.print("Enter Wikipedia Name: ");
        String subject = input.nextLine();
        FetchWikipedia wikipediaFetcher = new FetchWikipedia(subject); // add scanner testcase
        System.out.println(wikipediaFetcher.getConversion().getData().title);
    }

    @Test
    public void HardcodeTestCase() throws FetchWikipedia.NoSuchURLException, FetchWikipedia.CouldNotConvertToStringException, FetchWikipedia.BadConnectionException {
        FetchWikipedia wikiepediaFetcher = new FetchWikipedia("George Washington");
        System.out.println(wikiepediaFetcher.getConversion().getData().id);
    }
}
