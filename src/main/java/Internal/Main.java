package Internal;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        ArrayList<String> options = new ArrayList<>();
        options.add("(0) Quit ");
        options.add("(1) Search for page ");

        ArrayList<String> pageOptions = new ArrayList<>();
        pageOptions.add("(0) Back ");
        pageOptions.add("(1) Get page data ");
        pageOptions.add("(2) Search for revisions ");

        while (getAction(options) == 1) {
            JSONConvert jsonConvert = getJSONData();

            boolean inPage = true;
            while (inPage) {
                switch (getAction(pageOptions)) {
                    case 0: // Back
                        inPage = false;
                        break;
                    case 1: { // Get page data
                        System.out.println();
                        System.out.print("Page Title: ");
                        System.out.println(jsonConvert.getData().title);
                        System.out.print("Page Id: ");
                        System.out.println(jsonConvert.getData().id);
                        break;
                    }
                    case 2: { // Search for revisions
                        for (JSONConvert.Revision revision : jsonConvert.getData().revisions) {
                            printRevision(revision);
                        }
                        break;
                    }
                    default:
                        break;
                }
            }
        }
    }

    private static void printRevision(JSONConvert.Revision revision) {
        System.out.println();
        System.out.print("Edited by User: ");
        System.out.print(revision.user);
        System.out.print(" on ");

        StringBuilder correctedTime = new StringBuilder();

        String[] dateAndTme = revision.timestamp.split("T");
        String date = dateAndTme[0];
        String time = dateAndTme[1].replace('Z', ' ');

        String[] yearMonthDay = date.split("-");
        correctedTime.append(yearMonthDay[1]).append("/"); // Month
        correctedTime.append(yearMonthDay[2]).append("/"); // Day
        correctedTime.append(yearMonthDay[0]); // Year

        correctedTime.append(" at ");
        correctedTime.append(time);

        System.out.println(correctedTime);
    }

    private static int getAction(ArrayList<String> options) {
        System.out.println();
        int action = -1;
        while (action == -1) {
            for (String option : options) {
                System.out.println(option);
            }
            System.out.print("Enter Option: ");
            String sOption = input.nextLine();

            try {
                action = Integer.parseInt(sOption);
            } catch (NumberFormatException e) {
                System.out.println("Invalid Option, please try again.");
                continue;
            }
            if (action < 0 || action > options.size() - 1) {
                System.out.println("Invalid Option, please try again.");
                action = -1;
            }
        }
        return action;
    }

    private static JSONConvert getJSONData() {
        System.out.println();
        System.out.print("Enter Wikipedia Name: ");
        String subject = input.nextLine();

        if (subject.isEmpty()) {
            System.out.println("No page requested, please try again.");
            return getJSONData();
        }

        try {
            FetchWikipedia wikipediaFetch = new FetchWikipedia(subject);
            JSONConvert jsonConvert = wikipediaFetch.getConversion();

            if (!jsonConvert.getData().title.equalsIgnoreCase(subject)) {
                System.out.println();
                System.out.println("Redirected to " + jsonConvert.getData().title + ".");
                System.out.println();
            }

            return jsonConvert;
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
    }
}