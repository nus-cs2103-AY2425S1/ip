package yihuibot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Displays the messenger profile picture and the message.
 *
 * @author Toh Yi Hui A0259080A
 */
public class DialogBox extends HBox {

    private Label text;
    private ImageView displayPicture;

    /**
     * Constructor for a new DialogBox containing the messenger's
     * profile picture and its dialog.
     *
     * @param s the user's dialog.
     * @param i the user's profile picture.
     */
    private DialogBox(String s, Image i) {
        text = new Label(s);
        displayPicture = new ImageView(i);

        // Styling DialogBox
        text.setWrapText(true);
        displayPicture.setFitWidth(100.0);
        displayPicture.setFitHeight(100.0);
        this.setAlignment(Pos.TOP_RIGHT);

        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the DialogBox such that the ImageView is on the left and
     * the message is on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Factory method for a new user Dialog.
     *
     * @param s the user's dialog.
     * @param i the user's profile picture.
     * @return a right justified DialogBox.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Factory method for a bot Dialog.
     *
     * @param s the bot's dialog.
     * @param i the bot's profile picture.
     * @return a left justified DialogBox.
     */
    public static DialogBox getBotDialog(String s, Image i) {
        DialogBox dialogBox = new DialogBox(s, i);
        dialogBox.flip();
        return dialogBox;
    }
}
