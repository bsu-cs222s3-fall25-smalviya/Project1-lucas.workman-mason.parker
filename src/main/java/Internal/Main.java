package Internal;

import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        while (getPageAction() == 1) {
            JSONConvert jsonConvert = getJSONData();

            boolean inPage = true;
            while (inPage) {

                int action = getAction();

                switch (action) {
                    case 0: // Back
                        inPage = false;
                        break;
                    case 1: { // Get page data
                        System.out.println();
                        System.out.print("Page Title: ");
                        System.out.println(jsonConvert.getData().title);
                        System.out.print("Page Id: ");
                        System.out.println(jsonConvert.getData().id);
                        System.out.println();
                        break;
                    }
                    case 2: { // Search for last revision
                        JSONConvert.Revision revision = jsonConvert.getData().revisions.getFirst();

                        System.out.println();
                        System.out.print("Last edit by: ");
                        System.out.print(revision.user);
                        System.out.print(" at ");
                        System.out.println(revision.timestamp);
                        System.out.println();
                        break;
                    }
                    default:
                        break;
                }
            }
        }
    }

    private static int getPageAction() {
        int action = -1;
        while (action == -1) {
            System.out.println("(0) Quit ");
            System.out.println("(1) Search for page ");
            System.out.print("Enter Option: ");
            String sOption = input.nextLine();

            try {
                action = Integer.parseInt(sOption);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Option, please try again.");
                continue;
            }
            if (action < 0 || action > 1) {
                System.out.println("Invalid Option, please try again.");
                action = -1;
            }
        }
        return action;
    }

    private static int getAction() {
        int action = -1;
        while (action == -1) {
            System.out.println("(0) Back ");
            System.out.println("(1) Get page data ");
            System.out.println("(2) Search for last revision ");
            System.out.print("Enter Option: ");
            String sOption = input.nextLine();

            try {
                action = Integer.parseInt(sOption);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Option, please try again.");
                continue;
            }
            if (action < 0 || action > 2) {
                System.out.println("Invalid Option, please try again.");
                action = -1;
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