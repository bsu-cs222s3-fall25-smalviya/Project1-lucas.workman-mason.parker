package Internal;


import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

public class TestFile {


    @Test
    public void File() throws FetchWikipedia.NoSuchURLException, FetchWikipedia.CouldNotConvertToStringException, FetchWikipedia.BadConnectionException {
        FetchWikipedia wikipediaFetcher = new FetchWikipedia("George Washington");

    }
}
