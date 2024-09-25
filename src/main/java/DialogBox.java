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
     * Constructor for DialogBox. Takes in text and image to populate the dialog box.
     *
     * @param text Text to be displayed.
     * @param img Image to represent the speaker.
     */
    private DialogBox(String text, Image img) {
        assert text != null : "Text should not be null"; // Ensure text is not null
        assert img != null : "Image should not be null"; // Ensure image is not null

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            System.err.println("Error loading DialogBox FXML: " + e.getMessage()); // Avoid using empty catch blocks
        }

        // Ensure dialog and displayPicture are properly initialized
        assert dialog != null : "Dialog label should have been initialized";
        assert displayPicture != null : "Display picture should have been initialized";

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        assert tmp != null : "DialogBox children list should not be null"; // Ensure children list is valid

        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a user dialog with the specified text and image.
     *
     * @param text Text for the user dialog.
     * @param img Image representing the user.
     * @return DialogBox for the user.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        assert text != null && !text.isEmpty() : "User dialog text should not be null or empty"; // Ensure text is valid
        assert img != null : "User image should not be null"; // Ensure image is valid
        return new DialogBox(text, img);
    }

    /**
     * Creates a Dave dialog with the specified text and image. The dialog is flipped
     * so that the image is on the left and the text is on the right.
     *
     * @param text Text for the Dave dialog.
     * @param img Image representing Dave.
     * @return DialogBox for Dave.
     */
    public static DialogBox getDukeDialog(String text, Image img) {
        assert text != null && !text.isEmpty() : "Dave dialog text should not be null or empty"; // Ensure text is valid
        assert img != null : "Dave image should not be null"; // Ensure image is valid
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
