package edu.bsu.cs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestFile {

    @Test
    public void HardcodeTitleShouldMatch() throws FetchWikipedia.NoSuchURLException,
            FetchWikipedia.CouldNotConvertToStringException,
            FetchWikipedia.BadConnectionException {
        FetchWikipedia fetcher = new FetchWikipedia("Albert Einstein");
        String title = fetcher.getConversion().getData().title;
        Assertions.assertEquals("Albert Einstein", title);
    }

    @Test
    public void HardcodePageIdShouldNotBeZero() throws FetchWikipedia.NoSuchURLException,
            FetchWikipedia.CouldNotConvertToStringException,
            FetchWikipedia.BadConnectionException {
        FetchWikipedia fetcher = new FetchWikipedia("Isaac Newton");
        int pageId = fetcher.getConversion().getData().id;
        Assertions.assertTrue(pageId > 0, "Page id should be greater than zero");
    }

    @Test
    public void NoSuchPageShouldThrow() {
        Assertions.assertThrows(FetchWikipedia.NoSuchURLException.class, () -> {
            new FetchWikipedia("ThisPageShouldNotExist12345!!");
        });
    }

    @Test
    public void JSONConvertShouldParseValidData() throws Exception {
        // this should be able to test if json conversion works
        String mockJson = """
        {
          "query": {
            "pages": {
              "67420": {
                "pageid": 67,
                "title": "Test Subject",
                "revisions": [
                  {"user": "Tester", "timestamp": "2025-09-23T00:00:00Z"}
                ]
              }
            }
          }
        }
        """;

        JSONConvert converter = new JSONConvert(mockJson);
        Assertions.assertEquals(12345, converter.getData().id);
        Assertions.assertEquals("Test Subject", converter.getData().title);
        Assertions.assertEquals("Tester", converter.getData().revisions.getFirst().user);
    }
}

