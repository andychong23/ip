package gui;

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
    private final double hBoxOffset = 1.0;
    private final double textFieldScale = 0.8;
    private final double buttonScale = 0.2;
    private Bob bob;
    public Image bobImage = new Image(this.getClass().getResourceAsStream("/images/bob.jpg"));
    public Image userImage = new Image(this.getClass().getResourceAsStream("/images/gru.jpg"));

    /**
     * Constructor
     * @param minHeight Min height of the starting pane
     * @param minWidth Min width of the starting pane
     * @throws IOException When Bob object is failed to be created
     */
    public BobWindow(double minHeight, double minWidth) throws IOException {
        setDefaultWindow(minHeight, minWidth);

        bob = new Bob();

        handleUserLogIn();

        this.setOnKeyPressed((event) -> {
            scrollPane.setPrefHeight(this.getHeight() - hBox.getHeight() - hBoxOffset);
            if (isExit(event)) {
                handleExit();
            } else if (isEnter(event)) {
                handleEnter();
            }
        });

        this.button.setOnAction((event) -> {
            scrollPane.setPrefHeight(this.getHeight() - hBox.getHeight() - hBoxOffset);
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

    /**
     * Checks if textField contains bye to indicate that the program has ended
     * @param event KeyEvent that symbolises what key is being pressed
     * @return boolean to indicate if isExit
     */
    private boolean isExit(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER && textField.getText().equalsIgnoreCase("bye")) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the user uses the enter key
     * @param event KeyEvent that symbolises what key is being pressed
     * @return boolean to indicate if the enter key is pressed
     */
    private boolean isEnter(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            return true;
        }
        return false;
    }

    /**
     * Method to encapsulate all the tasks needed to be done when the user exits the program or when they input bye
     */
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

    /**
     * Method to handle all the required set-up to welcome the user
     */
    private void handleUserLogIn() {
        DialogBox bobDialog = DialogBox.createBobDialogBox(bobImage, bob.getWelcomeMessage());
        bobDialog.setAlignment(Pos.CENTER_LEFT);
        vBox.getChildren().add(bobDialog);
    }

    /**
     * Method to initialise all the height and widths of the objects in the window
     * @param minHeight Min height of the pane
     * @param minWidth Min widht of the pane
     */
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

    /**
     * Sets the default size for vBox
     * @param vBox VBox object
     */
    private void setDefaultVBox(VBox vBox) {
        vBox.setPrefWidth(scrollPane.getPrefWidth());
    }

    /**
     * Sets the default functionality for the ScrollPane
     * @param scrollPane ScrollPane object to be set
     */
    private void setDefaultScrollPane(ScrollPane scrollPane) {
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        scrollPane.vvalueProperty().bind(vBox.heightProperty());
    }

    /**
     * Set the default functionality of the TextField
     * @param textField TextField object to be set
     */
    private void setDefaultTextField(TextField textField) {
        textField.setPromptText("Input your request here");
    }

    /**
     * Method to ensure that enter is being handled the same way as a mouse click is
     */
    private void handleEnter() {
        handleButtonClick();
    }

    /**
     * Method to handle a button click
     */
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
