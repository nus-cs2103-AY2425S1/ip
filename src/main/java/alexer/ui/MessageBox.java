package alexer.ui;

import javafx.geometry.Pos;
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

        text.setWrapText(true);
        picture.setFitWidth(100.0);
        picture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);

        getChildren().addAll(text, picture);
    }
}
