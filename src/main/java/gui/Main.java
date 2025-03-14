package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
    double minHeight = 600;
    double minWidth = 400;

    @Override
    public void start(Stage stage) throws IOException {
        BobWindow anchorPane = new BobWindow(minHeight, minWidth);
        stage.setTitle("Bob");
        Scene scene = new Scene(anchorPane);
        anchorPane.setMinSize(scene.getWidth(), scene.getHeight());
        stage.widthProperty().addListener((observable, oldValue, newValue) -> {
            anchorPane.setMinWidth(newValue.doubleValue());
        });
        stage.heightProperty().addListener((observable, oldValue, newValue) -> {
            anchorPane.setMinHeight(newValue.doubleValue());
        });
        stage.setResizable(true);
        stage.getIcons().add(anchorPane.bobImage);

        stage.setScene(scene);
        stage.show();
    }
}
