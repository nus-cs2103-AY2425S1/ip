package drbrown.gui;

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
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 * This class is an extension of HBox, and it can be used to display user or system messages
 * in a chat-like interface.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     * Loads the FXML layout for the dialog box and initializes its components.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img  The image to be displayed next to the text in the dialog box.
     */
    private DialogBox(String text, Image img) {
        assert text != null : "Label text should not be null";
        assert img != null : "Image should not be null";
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
     * Flips the dialog box such that the ImageView is on the left and the text is on the right.
     * This is typically used to differentiate between user messages and system messages
     * in a chat interface.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box for user input with the specified text and image.
     * The text will be displayed on the right and the image on the left.
     *
     * @param text The text to be displayed.
     * @param img  The image to be displayed.
     * @return A DialogBox object representing the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        assert text != null : "Label text should not be null";
        assert img != null : "Image should not be null";
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box for system messages with the specified text and image.
     * The text will be displayed on the left and the image on the right by flipping the dialog box.
     *
     * @param text The text to be displayed.
     * @param img  The image to be displayed.
     * @return A DialogBox object representing the system's message.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        assert text != null : "Label text should not be null";
        assert img != null : "Image should not be null";
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
