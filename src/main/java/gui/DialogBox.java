package gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Collections;

public class DialogBox extends HBox {
    private final int IMAGE_WIDTH = 150;
    private final int IMAGE_HEIGHT = 150;

    public DialogBox(Image img, String text) {
        ImageView imageView = new ImageView(img);
        Label label = new Label(text);
        setImageViewSize(imageView);
        setLabelSize(label);
        this.getChildren().addAll(label, imageView);
    }

    private void setImageViewSize(ImageView imgView) {
        imgView.setFitWidth(IMAGE_WIDTH);
        imgView.setFitHeight(IMAGE_HEIGHT);
    }

    private void setLabelSize(Label label) {
        label.setWrapText(true);
    }

    public static DialogBox createUserDialogBox(Image img, String text) {
        return new DialogBox(img, text);
    }

    public static DialogBox createBobDialogBox(Image img, String text) {
        DialogBox dialogBox = new DialogBox(img, text);
        ObservableList<Node> temp = FXCollections.observableArrayList(dialogBox.getChildren());
        Collections.reverse(temp);
        dialogBox.getChildren().setAll(temp);
        return dialogBox;
    }
}
