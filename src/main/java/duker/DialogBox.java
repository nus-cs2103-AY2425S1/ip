package duker;

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
 * The DialogBox class represents a custom control that displays a dialog
 * box consisting of a text label and an image (e.g., a profile picture). It is used
 * in the Duker application to display user input and Duker's responses.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

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
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a flipped DialogBox that represents Duker's greeting message.
     *
     * @param text The greeting text to be displayed.
     * @param img  The image representing Duker.
     * @return A DialogBox containing Duker's greeting message.
     */
    public static DialogBox getDukerGreeting(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Creates a DialogBox that represents the user's dialog.
     *
     * @param text The text input by the user.
     * @param img  The image representing the user.
     * @return A DialogBox containing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a flipped DialogBox that represents Duker's dialog.
     *
     * @param text The response text from Duker.
     * @param img  The image representing Duker.
     * @return A DialogBox containing Duker's response dialog.
     */
    public static DialogBox getDukerDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
