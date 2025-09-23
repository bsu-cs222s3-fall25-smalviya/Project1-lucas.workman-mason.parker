package Internal;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        JSONConvert jsonConvert = getJSONData();
        System.out.println(jsonConvert.data.id);
        System.out.println(jsonConvert.data.title);
    }

    private static JSONConvert getJSONData() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Wikipedia Name: ");
        String subject = input.nextLine();
        FetchWikipedia wikipediaFetch;
        try {
            wikipediaFetch = new FetchWikipedia(subject);
        } catch (FetchWikipedia.NoSuchURLException e) {
            System.out.println("Could not find Page, please try again.");
            return getJSONData();
        } catch (FetchWikipedia.CouldNotConvertToStringException e) {
            System.out.println("Could not convert JSON, please try again.");
            return getJSONData();
        } catch (FetchWikipedia.BadConnectionException e) {
            System.out.println("Could not connect to Wikipedia, please try again.");
            return getJSONData();
        }
        return wikipediaFetch.getConversion();
    }
}