package gui;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import bob.Bob;

import java.io.IOException;

public class BobWindow extends AnchorPane {
    private ScrollPane scrollPane;
    private VBox vBox;
    private HBox hBox;
    private TextField textField;
    private Button button;
    private Image bobImage = new Image(this.getClass().getResourceAsStream("/images/bob.jpg"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/gru.jpg"));
    private final double hBoxOffset = 1.0;
    private final double textFieldScale = 0.8;
    private final double buttonScale = 0.2;
    private Bob bob;

    public BobWindow(double minHeight, double minWidth) throws IOException {
        setDefaultWindow(minHeight, minWidth);

        bob = new Bob();

        handleUserLogIn();

        this.setOnKeyPressed((event) -> {
            if (isExit(event)) {
                handleExit();
            } else if (isEnter(event)) {
                handleEnter();
            }
        });

        this.button.setOnAction((event) -> {
            handleButtonClick();
        });

        this.widthProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setPrefWidth(newValue.doubleValue());
            hBox.setPrefWidth(newValue.doubleValue());
            textField.setPrefWidth(hBox.getPrefWidth() * textFieldScale);
            button.setPrefWidth(hBox.getPrefWidth() * buttonScale);
        });

        this.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setPrefHeight(newValue.doubleValue() - hBox.getHeight() - hBoxOffset);
        });

    }

    public void setSize(double width, double height) {
        scrollPane.setPrefSize(width, height);
        vBox.setPrefSize(width, height);
        hBox.setPrefWidth(width);
    }

    private boolean isExit(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && textField.getText().equalsIgnoreCase("bye")) {
            return true;
        }
        return false;
    }

    private boolean isEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            return true;
        }
        return false;
    }

    private void handleExit() {
        DialogBox userDialog = DialogBox.createUserDialogBox(userImage, textField.getText());
        DialogBox bobDialog = DialogBox.createBobDialogBox(bobImage, bob.getExitMessage());

        userDialog.setAlignment(Pos.CENTER_RIGHT);
        bobDialog.setAlignment(Pos.CENTER_LEFT);

        vBox.getChildren().addAll(userDialog, bobDialog);
        textField.clear();

        textField.setEditable(false);
        button.setDisable(true);

        // make it such that user cannot provide any more functionality after saying bye
        setOnKeyPressed((event) -> {});
    }

    private void handleUserLogIn() {
        DialogBox bobDialog = DialogBox.createBobDialogBox(bobImage, bob.getWelcomeMessage());
        bobDialog.setAlignment(Pos.CENTER_LEFT);
        vBox.getChildren().add(bobDialog);
    }

    private void setDefaultWindow(double minHeight, double minWidth) {
        this.setPrefSize(minWidth, minHeight);

        scrollPane = new ScrollPane();
        vBox = new VBox();

        scrollPane.setContent(vBox);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);

        hBox = new HBox();
        textField = new TextField();
        button = new Button("Send");

        hBox.getChildren().addAll(textField, button);
        hBox.setPrefWidth(this.getPrefWidth());
        textField.setPrefWidth(hBox.getPrefWidth() * textFieldScale);
        button.setPrefWidth(hBox.getPrefWidth() * buttonScale);

        setDefaultScrollPane(scrollPane);
        setDefaultTextField(textField);
        setTopAnchor(scrollPane, 0.0);
        setRightAnchor(scrollPane, 0.0);
        setLeftAnchor(scrollPane, 0.0);

        setLeftAnchor(hBox, 0.0);
        setRightAnchor(hBox, 0.0);
        setBottomAnchor(hBox, hBoxOffset);
        setDefaultVBox(vBox);


        this.getChildren().addAll(scrollPane, hBox);
    }

    private void setDefaultVBox(VBox vBox) {
        vBox.setPrefWidth(scrollPane.getPrefWidth());
    }

    private void setDefaultScrollPane(ScrollPane scrollPane) {
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
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
        DialogBox userDialog;
        DialogBox bobDialog;

        userDialog = DialogBox.createUserDialogBox(userImage, textField.getText());

        try {
            bobDialog = DialogBox.createBobDialogBox(bobImage, bob.parseInput(textField.getText()));
        } catch (Exception e) {

            bobDialog = DialogBox.createBobDialogBox(bobImage, "An error occurred, please try again");
        }

        userDialog.setAlignment(Pos.CENTER_RIGHT);
        bobDialog.setAlignment(Pos.CENTER_LEFT);

        vBox.getChildren().addAll(userDialog, bobDialog);
        textField.clear();
    }
}
