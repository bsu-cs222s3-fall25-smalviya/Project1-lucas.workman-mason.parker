package Internal;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URLConnection;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, URISyntaxException {

        Scanner input = new Scanner(System.in);
        System.out.print("Enter Wikipedia Name: ");
        String subject = input.nextLine();
        FetchWikipedia wikipediaFetch = new FetchWikipedia(subject);
        System.out.println(wikipediaFetch.getConversion().data.revisions.get(0).user);
    }
}