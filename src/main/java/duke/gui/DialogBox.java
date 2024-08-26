package duke.gui;

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
import javafx.scene.shape.Circle;

/**
 * Represents a dialog box in the GUI.
 * This class extends HBox and is used to display messages and profile pictures in a chat-like interface.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox with the given text and image.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be used as the profile picture.
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

        Circle shape = new Circle(50, 50, 50);

        dialog.setText(text);
        displayPicture.setImage(img);
        displayPicture.setClip(shape);
    }

    /**
     * Flips the dialog box horizontally.
     * This is used to change the alignment of the dialog box for different speakers.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a DialogBox for Duke's introduction.
     *
     * @param text The introduction text.
     * @param img Duke's profile picture.
     * @return A DialogBox instance for Duke's introduction.
     */

    public static DialogBox getDukeIntro(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Creates a DialogBox for the user's message.
     *
     * @param text The user's message.
     * @param img The user's profile picture.
     * @return A DialogBox instance for the user's message.
     */

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a DialogBox for Duke's message.
     *
     * @param text Duke's message.
     * @param img Duke's profile picture.
     * @return A DialogBox instance for Duke's message.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
