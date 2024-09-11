package controller;

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
     * Constructs a dialog box with the specified text and image.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed in the dialog box.
     */
    private DialogBox(String text, Image img) {
        assert text != null && !text.isEmpty() : "Text should not be null or empty";
        assert img != null : "Image should not be null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert dialog != null : "Dialog Label should be initialized";
        assert displayPicture != null : "ImageView should be initialized";

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        assert !this.getChildren().isEmpty() : "DialogBox should contain children before flipping";
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Gets a dialog box for the user with the specified text and image.
     *
     * @param img The image to be displayed in the dialog box.
     * @param text The text to be displayed in the dialog box.
     * @return The dialog box for the user.
     */
    public static DialogBox getUserDialog(Image img, String... text) {
        assert text.length > 0 : "Text for user dialog should be provided";
        assert img != null : "User image should not be null";

        return new DialogBox(text[0], img);
    }

    /**
     * Gets a dialog box for Friday chatbot with the specified text and image.
     *
     * @param img The image to be displayed in the dialog box.
     * @param text The text to be displayed in the dialog box.
     * @return The dialog box for Friday.
     */
    public static DialogBox getFridayDialog(Image img, String... text) {
        assert text.length > 0 : "Text for Friday dialog should be provided";
        assert img != null : "Friday image should not be null";

        var db = new DialogBox(text[0], img);
        db.flip();
        return db;
    }
}

