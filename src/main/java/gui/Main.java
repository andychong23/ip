package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{
    double minHeight = 600;
    double minWidth = 400;

    @Override
    public void start(Stage stage) {
        AnchorPane anchorPane = new BobWindow(minHeight, minWidth);

        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }
}
