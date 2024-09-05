package niko.main;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

public class DialogBox extends HBox {

    public DialogBox(Label text, ImageView displayPicture) {
        text.setWrapText(true);
        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);

        this.getChildren().addAll(displayPicture, text);
    }

    public static DialogBox getUserDialog(String text, Image img) {
        Label label = new Label(text);
        ImageView imageView = new ImageView(img);
        return new DialogBox(label, imageView);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        Label label = new Label(text);
        ImageView imageView = new ImageView(img);
        return new DialogBox(label, imageView);
    }
}
