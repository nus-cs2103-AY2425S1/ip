package torne.ui;

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
 * <p></p>
 * Trimmed down to just the *controller* aspect.
 * All the styling and formatting is moved to the FXML file! :)
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            // All this is the alternative to using fx:controller="..." as in MainWindow.fxml
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
     * Formats this dialog box for an error message.
     */
    private void formatAsError() {
        dialog.getStyleClass().add("error-dialog"); // add CSS class with error formatting
    }

    /**
     * Creates a standard dialog box for the user. Right-aligned.
     *
     * @param text text content of the dialog box.
     * @param img image to be shown alongside the dialog box.
     * @return {@link DialogBox} object.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Creates a standard dialog box for Torne (the bot). Left-aligned.
     *
     * @param text text content of the dialog box.
     * @param img image to be shown alongside the dialog box.
     * @return {@link DialogBox} object.
     */
    public static DialogBox getTorneDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Creates an error dialog box for Torne (the bot). Left-aligned.
     * <br>
     * Has additional error formatting.
     *
     * @param text error text content of the dialog box.
     * @param img image to be shown alongside the dialog box.
     * @return {@link DialogBox} object.
     */
    public static DialogBox getErrorDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        db.formatAsError();
        return db;
    }
}
