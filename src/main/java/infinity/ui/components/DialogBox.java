package infinity.ui.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    public DialogBox(String s, Image i, boolean reverse) {
        this.text = new Label(s);
        this.displayPicture = new ImageView(i);

        //Styling the dialog box
        this.text.setWrapText(true);
        this.text.setMinHeight(48.0);
        this.text.setAlignment(reverse ? Pos.BOTTOM_LEFT : Pos.BOTTOM_RIGHT);
        this.text.setTextAlignment(reverse ? TextAlignment.LEFT : TextAlignment.RIGHT);
        this.text.setPadding(new Insets(0, 10, 5, 15));
        this.displayPicture.setFitWidth(48.0);
        this.displayPicture.setFitHeight(48.0);
        this.setPadding(new Insets(10.0, 10.0, 10.0, 10.0));
        this.setAlignment(reverse ? Pos.BOTTOM_LEFT : Pos.BOTTOM_RIGHT);

        if (reverse) {
            this.getChildren().addAll(displayPicture, text);
        } else {
            this.getChildren().addAll(text, displayPicture);
        }
    }
}
