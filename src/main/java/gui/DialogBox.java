package gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class DialogBox extends HBox {
    public DialogBox(Image img, String text) {
        ImageView imageView = new ImageView(img);
        Label label = new Label(text);

        this.getChildren().addAll(imageView, label);
    }

    private void setImageViewSize(ImageView imgView) {
        imgView.setFitWidth(this.getWidth() * 0.8);
        imgView.setFitHeight(this.getHeight());
    }

    private void setLabelSize(Label label) {
        label.setWrapText(true);
        label.setPrefWidth(this.getWidth() * 0.2);
        label.setPrefHeight(this.getHeight());
    }
}
