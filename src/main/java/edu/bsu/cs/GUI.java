package edu.bsu.cs;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class GUI extends Application {

    TextField inputField;
    Label outputLabel;

    @Override
    public void start(Stage stage) {

        Label title = new Label("Wikipedia Web Parser");

        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 10;");

        GridPane pane = new GridPane();

        inputField = new TextField();
        inputField.setPromptText("Search Item");
        inputField.setPrefHeight(25);
        inputField.setPrefWidth(55);

        outputLabel = new Label();

        Button search = new javafx.scene.control.Button("Search");
        GridPane.setConstraints(search, 1, 0);
        search.setPrefHeight(25);
        search.setOnAction(this::onSearch);

        pane.getChildren().addAll(inputField, search);
        root.getChildren().addAll(title, pane, outputLabel);

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("GUI Test");
        stage.show();
    }

    void onSearch(ActionEvent actionEvent) {
        JSONConvert jsonConvert = getJSONData();

        for (JSONConvert.Revision revision : jsonConvert.getData().revisions) {
            StringBuilder formattedString = new StringBuilder();

            String[] dateAndTme = revision.timestamp.split("T");
            String date = dateAndTme[0];
            String time = dateAndTme[1].replace('Z', ' ');

            String[] yearMonthDay = date.split("-");
            formattedString.append(yearMonthDay[1]).append("/"); // Month
            formattedString.append(yearMonthDay[2]).append("/"); // Day
            formattedString.append(yearMonthDay[0]); // Year

            formattedString.append(" at ");
            formattedString.append(time);

            outputLabel.setText(formattedString.toString());

        }
    }

    private JSONConvert getJSONData() {
        String subject = inputField.getText();

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
