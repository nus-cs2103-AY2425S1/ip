package edith.guicontrol;

import edith.MainWindow;

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
 * Represents a dialog box consisting of an ImageView to display a profile picture (for the user or chatbot)
 * and a label to show text from the respective profile.
 */
public class DialogBox extends HBox {
    /** The label that contains the text of the dialog. */
    @FXML
    private Label dialog;

    /** The ImageView that displays the profile picture (either the user or Edith). */
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     * Loads the DialogBox layout from an FXML file.
     *
     * @param text The text to be displayed in the dialog.
     * @param img The image to be displayed as the profile picture.
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
     * Flips the dialog box such that the ImageView is on the left and the text is on the right.
     * This is used for Edith's responses.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates and returns a DialogBox for the user's dialog.
     *
     * @param text The text of the user's input.
     * @param img The image representing the user.
     * @return A DialogBox containing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates and returns a DialogBox for Edith's dialog.
     * The dialog box is flipped to distinguish it from the user's dialog.
     *
     * @param text The text of Edith's response.
     * @param img The image representing Edith.
     * @return A DialogBox containing Edith's dialog.
     */
    public static DialogBox getEdithDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
