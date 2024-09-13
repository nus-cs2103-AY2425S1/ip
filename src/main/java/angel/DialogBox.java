package main.java.angel;

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
     * Constructs a DialogBox object with the specified text and image.
     * Loads the FXML layout and initializes the text and image for the dialog box.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img  The image to be displayed in the ImageView.
     */
    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(GuiWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert text != null && !text.isEmpty() : "Dialog text should not be null or empty";
        assert img != null : "Image should not be null";

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

        // Assert that the flip operation correctly reorders the dialog box components
        assert getChildren().size() == tmp.size() : "Children size mismatch after flipping";
        assert getAlignment() == Pos.TOP_LEFT : "Alignment should be TOP_LEFT after flipping";
    }

    /**
     * Creates and returns a dialog box for the user's dialog with the specified text and image.
     *
     * @param text The text to be displayed in the user's dialog box.
     * @param img  The image to be displayed in the user's dialog box.
     * @return A DialogBox object representing the user's dialog.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates and returns a dialog box for Angel's dialog with the specified text and image.
     * The dialog box is flipped so that the image appears on the left side.
     *
     * @param text The text to be displayed in Angel's dialog box.
     * @param img  The image to be displayed in Angel's dialog box.
     * @return A DialogBox object representing Angel's dialog.
     */
    public static DialogBox getAngelDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
