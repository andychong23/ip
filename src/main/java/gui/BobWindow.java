package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class BobWindow extends AnchorPane {
    private ScrollPane scrollPane;
    private VBox vBox;
    private HBox hBox;
    private TextField textField;
    private Button button;
    private Image bobImage = new Image(this.getClass().getResourceAsStream("/images/bob.jpg"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/gru.jpg"));

    public BobWindow(double minHeight, double minWidth) {
        setDefaultWindow(minHeight, minWidth);

        this.setOnKeyPressed((event) -> {
            if (event.getCode() == KeyCode.ENTER) {
                handleEnter();
            }
        });
        this.button.setOnAction((event) -> {
            handleButtonClick();
        });
    }

    private void setDefaultWindow(double minHeight, double minWidth) {
        this.setPrefSize(minWidth, minHeight);

        scrollPane = new ScrollPane();
        vBox = new VBox();

        scrollPane.setContent(vBox);

        hBox = new HBox();
        textField = new TextField();
        button = new Button("Send");

        hBox.getChildren().addAll(textField, button);
        hBox.setPrefWidth(this.getPrefWidth());
        textField.setPrefWidth(hBox.getPrefWidth() * 0.8);
        button.setPrefWidth(hBox.getPrefWidth() * 0.2);

        setDefaultScrollPane(scrollPane);
        setDefaultTextField(textField);
        setTopAnchor(scrollPane, 0.0);
        setBottomAnchor(hBox, 0.0);
        setDefaultVBox(vBox);

        this.getChildren().addAll(scrollPane, hBox);
    }

    private void setDefaultVBox(VBox vBox) {
        vBox.setPrefWidth(scrollPane.getPrefWidth());
    }

    private void setDefaultScrollPane(ScrollPane scrollPane) {
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefSize(this.getPrefWidth(), this.getPrefHeight());
        scrollPane.vvalueProperty().bind(vBox.heightProperty());
    }

    private void setDefaultTextField(TextField textField) {
        textField.setPromptText("Input your request here");
    }

    private void handleEnter() {
        handleButtonClick();
    }

    private void handleButtonClick() {
        DialogBox userDialog = DialogBox.createUserDialogBox(userImage, textField.getText());
        DialogBox bobDialog = DialogBox.createBobDialogBox(bobImage, "this is a test");

        userDialog.setAlignment(Pos.CENTER_RIGHT);
        bobDialog.setAlignment(Pos.CENTER_LEFT);

        vBox.getChildren().addAll(userDialog, bobDialog);
        textField.clear();
    }
}
