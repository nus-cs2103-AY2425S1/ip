package mysutong;

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

import java.io.IOException;
import java.util.Collections;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 * This class manages the layout of dialog boxes for both the user and Duke in the GUI.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;  // The label containing the dialog text.
    @FXML
    private ImageView displayPicture;  // The ImageView displaying the speaker's image.

    /**
     * Constructs a DialogBox with the specified text and image.
     * This loads the FXML file and sets the text and image for the dialog box.
     *
     * @param text The text to display in the dialog box.
     * @param img  The image to display next to the dialog.
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
     * Flips the dialog box such that the ImageView is on the left and text is on the right.
     * This is used for differentiating the dialog box of Duke (flipped) from the user's dialog box (normal).
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);  // Align the flipped dialog box to the top left.
    }

    /**
     * Creates a dialog box for the user's input.
     * The user's dialog box is not flipped (ImageView on the right, text on the left).
     *
     * @param text The text entered by the user.
     * @param img  The image representing the user.
     * @return A DialogBox representing the user's input.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a dialog box for Duke's response.
     * Duke's dialog box is flipped (ImageView on the left, text on the right).
     *
     * @param text The text of Duke's response.
     * @param img  The image representing Duke.
     * @return A DialogBox representing Duke's response, with the ImageView flipped to the left.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
