package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Collections;

public class DialogBox extends HBox {
    private final int IMAGE_WIDTH = 150;
    private final int IMAGE_HEIGHT = 150;

    /**
     * Constructor for a DialogBox
     * @param img Image object to be displayed
     * @param text String object to be displayed
     */
    public DialogBox(Image img, String text) {
        ImageView imageView = new ImageView(img);
        Label label = new Label(text);
        setImageViewSize(imageView);
        setLabelProperty(label);
        this.getChildren().addAll(label, imageView);
    }

    /**
     * Method to set the Image size
     * @param imgView ImageView object encapsulates the Image object being shown
     */
    private void setImageViewSize(ImageView imgView) {
        imgView.setFitWidth(IMAGE_WIDTH);
        imgView.setFitHeight(IMAGE_HEIGHT);
    }


    /**
     * Sets all properties relating to the Label object
     * @param label Label object
     */
    private void setLabelProperty(Label label) {
        label.setWrapText(true);
        label.setPadding(new Insets(0, 5, 0, 5));
    }

    /**
     * Create a user dialog box
     * @param img Image of the user
     * @param text Text input by the user
     * @return DialogBox that contains the user's image and text
     */
    public static DialogBox createUserDialogBox(Image img, String text) {
        return new DialogBox(img, text);
    }

    /**
     * Create a bob dialog box
     * @param img Image of bob
     * @param text Text returned by bob
     * @return DialogBox that contains bob's image and text
     */
    public static DialogBox createBobDialogBox(Image img, String text) {
        DialogBox dialogBox = new DialogBox(img, text);
        ObservableList<Node> temp = FXCollections.observableArrayList(dialogBox.getChildren());
        Collections.reverse(temp);
        dialogBox.getChildren().setAll(temp);
        return dialogBox;
    }
}
