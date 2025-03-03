package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
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
        scene.setOnDragDone((event) -> {
            anchorPane.setMinSize(scene.getWidth(), scene.getHeight());
            anchorPane.setSize(scene.getWidth(), scene.getHeight());
        });
        stage.setScene(scene);
        stage.show();
    }
}
