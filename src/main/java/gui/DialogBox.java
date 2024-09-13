package gui;

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
 * Represents a dialog box consisting of an ImageView to represent the speaker's face and a label containing text from
 * the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a dialog box with the given string and image.
     *
     * @param string The string to be displayed.
     * @param image The image to be displayed.
     */
    public DialogBox(String string, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(string);
        displayPicture.setImage(image);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Returns a dialog box with the given string and image.
     *
     * @param string The string to be displayed.
     * @param image The image to be displayed.
     * @return A dialog box with the given string and image.
     */
    public static DialogBox getUserDialog(String string, Image image) {
        return new DialogBox(string, image);
    }

    /**
     * Returns a dialog box with the given string and image, flipped such that the ImageView is on the left and text on
     * the right.
     *
     * @param string The string to be displayed.
     * @param image The image to be displayed.
     * @return A dialog box with the given string and image, flipped.
     */
    public static DialogBox getDynamikeDialog(String string, Image image) {
        var db = new DialogBox(string, image);
        db.flip();
        return db;
    }

}
