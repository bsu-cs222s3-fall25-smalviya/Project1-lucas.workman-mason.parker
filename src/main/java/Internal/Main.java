package Internal;

import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        JSONConvert jsonConvert = getJSONData();
        System.out.println(jsonConvert.data.id);
        System.out.println(jsonConvert.data.title);
    }

    private static int getAction() {
        int action = 0;
        while (action == 0) {
            System.out.println("Enter Option: ");
            System.out.println("(1) Search for last revision ");
            System.out.println("(2)");
            System.out.println("(3)");
            String sOption = input.nextLine();

            try {
                action = Integer.parseInt(sOption);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Option, please try again.");
                continue;
            }
            if (action < 1 || action > 3) {
                System.out.println("Invalid Option, please try again.");
                action = 0;
            }
        }
        return action;
    }

    private static JSONConvert getJSONData() {
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