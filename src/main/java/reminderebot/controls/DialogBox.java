package reminderebot.controls;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Class DialogBox is a controller for dialog boxes.
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Instantiate a DialogBox for GUI.
     * @param message
     * @param profile
     */
    public DialogBox(String message, Image profile) {
        text = new Label(message);
        displayPicture = new ImageView(profile);

        //Styling the dialog box
        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);

        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Gets user dialog.
     * @param s
     * @param i
     * @return DialogBox representing user dialog.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Gets Reminderebot dialog.
     * @param s
     * @param i
     * @return DialogBox representing Reminderebot dialog.
     */
    public static DialogBox getReminderebotDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}
