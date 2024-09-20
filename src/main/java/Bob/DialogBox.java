package bob;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box in the Bob application.
 */
public class DialogBox extends HBox {

    private final Label text;
    private final ImageView displayPicture;

    /**
     * Constructor for a dialog box in the Bob application.
     *
     * @param s The text to be displayed in the dialog box.
     * @param i The image to be displayed in the dialog box.
     */
    public DialogBox(String s, Image i) {
        text = new Label(s);
        displayPicture = new ImageView(i);

        //Styling the dialog box
        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);

        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on
     * the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> list = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(list);
        this.getChildren().setAll(list);
    }

    public static DialogBox getUserDialog(String text, Image image) {
        return new DialogBox(text, image);
    }

    /**
     * Returns a dialog box with the text and image of the Duke chat bot. 
     * 
     * @param text
     * @param image
     * @return
     */
    public static DialogBox getDukeDialog(String text, Image image) {
        var db = new DialogBox(text, image);
        db.flip();
        return db;
    }
}
