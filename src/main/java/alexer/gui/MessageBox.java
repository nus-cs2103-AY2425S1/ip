package alexer.gui;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MessageBox extends HBox {
    private final Label text;
    private final ImageView picture;

    public MessageBox(String message, Image displayPicture) {
        text = new Label(message);
        picture = new ImageView(displayPicture);
        getChildren().addAll(text, picture);
    }
}
