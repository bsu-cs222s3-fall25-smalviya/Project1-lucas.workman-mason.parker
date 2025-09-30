package Internal;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.control.Button;

import java.awt.*;

public class GUI extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("Wikipedia Web Parser");

        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 10;");

        GridPane pane = new GridPane();



        final TextField textField = new TextField ();
        textField.setPromptText("Search Item");
       // textField.setPrefColumnCount(5);
        textField.setPrefHeight(25);
        textField.setPrefWidth(55);
        textField.getText();
       // pane.getChildren().add(title);

        Button search = new javafx.scene.control.Button("Search");
        GridPane.setConstraints(search, 1, 0);
       pane.getChildren().add(search);

       search.setOnAction(new EventHandler<ActionEvent>() {
           @Override
           public void handle(ActionEvent actionEvent) {
               System.out.println(textField.getText());
           }
       });


        pane.getChildren().add(textField);



        root.getChildren().addAll(title, pane);

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("GUI Test");
        stage.show();
    }
}
