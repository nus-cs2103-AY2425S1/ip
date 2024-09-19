package sadcat.gui;

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

        dialog.setText(text);
        displayPicture.setImage(img);

        Circle clip = new Circle(30, 30, 30);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box horizontally.
     * This is used to change the alignment of the dialog box for different speakers.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a DialogBox for SadCat's introduction.
     *
     * @param text The introduction text.
     * @param img SadCat's profile picture.
     * @return A DialogBox instance for SadCat's introduction.
     */

    public static DialogBox getSadCatIntro(String text, Image img) {
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
     * Creates a DialogBox for SadCat's message.
     *
     * @param text SadCat's message.
     * @param img SadCat's profile picture.
     * @return A DialogBox instance for SadCat's message.
     */
    public static DialogBox getSadCatDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
