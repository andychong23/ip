package gui;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class BobWindow extends AnchorPane {
    private ScrollPane scrollPane;
    private VBox vBox;
    private TextField textField;
    private Button button;

    public BobWindow(double minHeight, double minWidth) {
        this.setMinHeight(minHeight);
        this.setMinWidth(minWidth);

        scrollPane = new ScrollPane();

        vBox = new VBox();

        textField = new TextField();
        button = new Button("Send");

        vBox.getChildren().addAll(scrollPane, textField, button);

        setDefaultScrollPane(scrollPane);
        setDefaultVBox(vBox);

        this.getChildren().addAll(vBox);
    }

    private void setDefaultScrollPane(ScrollPane scrollPane) {
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
    }

    private void setDefaultVBox(VBox vBox) {
        vBox.setMinSize(this.getMinWidth(), this.getMinHeight());
    }
}
