package gui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a Dialogue Box
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for a dialogue
     * @param dialogue The dialogue to be displayed
     * @param image The image of the user/bot
     */
    public DialogBox(String dialogue, Image image) {
        text = new Label(dialogue);
        displayPicture = new ImageView(image);
        this.getChildren().addAll(text, displayPicture);

        //Styling the dialog box
        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);
    }
}
