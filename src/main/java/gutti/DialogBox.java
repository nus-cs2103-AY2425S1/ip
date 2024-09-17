package gutti;

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
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     * Loads the corresponding FXML layout and sets the text and image for the dialog.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img  The image representing the speaker.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
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
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a dialog box for the user.
     * The image and text are aligned with the user's perspective (text on the right, image on the left).
     *
     * @param text The user's input text to display.
     * @param img  The user's avatar image.
     * @return A DialogBox representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box for Gutti.
     * The image and text are flipped (text on the left, image on the right).
     *
     * @param text The response text from Gutti.
     * @param img  The image representing Gutti.
     * @return A DialogBox representing Gutti's dialog, with the image on the left and text on the right.
     */
    public static DialogBox getGuttiDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}