package nayana;

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
    private Label dialog; // The label that displays the dialog text.
    @FXML
    private ImageView displayPicture; // The ImageView that shows the speaker's image.

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);
            fxmlLoader.load(); // Loads the FXML layout for the dialog box.
        } catch (IOException e) {
            e.printStackTrace(); // Handles potential I/O errors during FXML loading.
        }

        dialog.setText(text); // Sets the text for the dialog label.
        displayPicture.setImage(img); // Sets the image for the ImageView.
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp); // Reverses the order of the children.
        getChildren().setAll(tmp); // Updates the HBox with the reversed order.
        setAlignment(Pos.TOP_LEFT); // Sets the alignment of the dialog box.
    }

    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img); // Creates and returns a new user dialog box.
    }

    public static DialogBox getNayanaDialog(String text, Image img) {
        var db = new DialogBox(text, img); // Creates a new DialogBox instance.
        db.flip(); // Flips the dialog box to adjust the layout.
        return db; // Returns the flipped dialog box.
    }
}
