package bing;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;


public class DialogBox extends HBox{

    private Label text;
    private ImageView displayPicture;

    public DialogBox(String labelText, Image img, boolean isBot) {
        assert labelText != null : "Label text cannot be null.";
        assert img != null : "Image cannot be null.";

        text = new Label(labelText);
        displayPicture = new ImageView(img);
        text.setWrapText(true);

        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);
        displayPicture.setClip(new Circle(25, 25, 25));


        if (isBot) {
            this.getChildren().addAll(displayPicture, text);
            this.setAlignment(Pos.TOP_LEFT);
        } else {
            this.getChildren().addAll(text, displayPicture);
            this.setAlignment(Pos.TOP_RIGHT);
        }
    }

    public static DialogBox getUserDialog(String input) {
        return createDialog(input, "/images/user.png", Pos.TOP_RIGHT, false);
    }

    public static DialogBox getBingDialog(String response) {
        return createDialog(response, "/images/bot.png", Pos.TOP_LEFT, true);
    }

    private static DialogBox createDialog(String input, String imagePath, Pos alignment, boolean isBot) {
        Image img = new Image(imagePath);
        if (img.isError()) {
            System.out.println("Error loading image from: " + imagePath);
        }
        DialogBox dialog = new DialogBox(input, img, isBot);
        dialog.setAlignment(alignment);
        return dialog;
    }
}
