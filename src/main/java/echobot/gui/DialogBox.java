package echobot.gui;

import java.io.IOException;

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
 * A custom control representing a dialog box
 * consisting of a label containing text and an image
 * representing the speaker. This class extends
 * {@code HBox} to arrange the components horizontally.
 * It provides static methods to generate dialog boxes
 * for both the user and Duke, with an option to flip
 * the dialog box to differentiate between the two.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     * Loads the FXML layout for the dialog box and sets its content.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img  The image to be displayed alongside the text in the dialog box.
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
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates and returns a DialogBox for the user with the specified text and image.
     *
     * @param s The text to be displayed in the user's dialog box.
     * @param i The image to be displayed alongside the text in the user's dialog box.
     * @return A DialogBox representing the user's dialog.
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Creates and returns a DialogBox for Duke with the specified text and image.
     * The dialog box is flipped to distinguish it from the user's dialog.
     *
     * @param s The text to be displayed in Duke's dialog box.
     * @param i The image to be displayed alongside the text in Duke's dialog box.
     * @return A DialogBox representing Duke's dialog.
     */
    public static DialogBox getDukeDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }

}
