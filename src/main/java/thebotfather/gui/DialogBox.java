package thebotfather.gui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A custom dialog box that consists of an {@code ImageView} to represent the speaker's face
 * and a {@code Label} to display the speaker's message.
 * <p>
 * This class provides methods to create dialog boxes for both the user and the bot,
 * with an option to "flip" the bot's dialog for visual distinction.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a dialog box with the given text and image.
     * <p>
     * Loads the FXML layout and initializes the dialog text and display picture.
     *
     * @param text The message to be displayed in the dialog box.
     * @param img The image representing the speaker.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the {@code ImageView} is positioned to the left
     * and the {@code Label} containing the text is on the right.
     * <p>
     * This method is typically used for dialog boxes representing the bot's responses.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Returns a dialog box for the user with the given text and image.
     *
     * @param text The message from the user.
     * @param img The image representing the user.
     * @return A {@code DialogBox} containing the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Returns a dialog box for the bot with the given text and image, with the dialog flipped
     * to distinguish it from the user's dialog.
     *
     * @param text The message from the bot.
     * @param img The image representing the bot.
     * @return A {@code DialogBox} containing the bot's message.
     */
    public static DialogBox getBossDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
