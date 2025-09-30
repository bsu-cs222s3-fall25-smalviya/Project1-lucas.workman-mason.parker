package Internal;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

    @Override
    public void start(Stage stage) {

        Label title = new Label("Label");

        VBox root = new VBox(10);
        root.setStyle("-fx-padding: 10;");

        root.getChildren().addAll(title);

        Scene scene = new Scene(root, 600, 400);
        stage.setScene(scene);
        stage.setTitle("GUI Test");
        stage.show();
    }
}
