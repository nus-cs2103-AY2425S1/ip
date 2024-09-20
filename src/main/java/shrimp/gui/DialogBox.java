package shrimp.gui;

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
 * A custom control representing a dialog box containing text and an image (e.g., user or chatbot).
 * This class extends {@link HBox} and allows displaying dialog messages along with images.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} with the specified text message and image.
     * The FXML layout is loaded from {@code /view/DialogBox.fxml} and initialized.
     *
     * @param messageText The text message to be displayed in the dialog box.
     * @param image The image to be displayed next to the text message (e.g., user or bot's profile picture).
     */
    private DialogBox(String messageText, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(messageText);
        displayPicture.setImage(image);

        // Set the display picture in a circular clip
        Circle clip = new Circle(30, 30, 30);
        displayPicture.setClip(clip);
    }

    /**
     * Flips the dialog box layout, switching the position of the text and image.
     * This is used to differentiate between user and bot messages.
     */
    private void flip() {
        ObservableList<Node> childNode = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(childNode);
        getChildren().setAll(childNode);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label"); // Style as bot reply
    }

    /**
     * Creates a dialog box for the user.
     *
     * @param messageText The user's message to be displayed.
     * @param userImage The image representing the user.
     * @return A {@code DialogBox} with the user's message and image.
     */
    public static DialogBox getUserDialog(String messageText, Image userImage) {
        var db = new DialogBox(messageText, userImage);
        db.dialog.getStyleClass().add("label"); // Style as user dialog
        return db;
    }

    /**
     * Creates a dialog box for Shrimp (the chatbot).
     * This method flips the layout so the image appears on the left.
     *
     * @param messageText The chatbot's message to be displayed.
     * @param shrimpImage The image representing Shrimp (the chatbot).
     * @return A flipped {@code DialogBox} with Shrimp's message and image.
     */
    public static DialogBox getShrimpDialog(String messageText, Image shrimpImage) {
        var db = new DialogBox(messageText, shrimpImage);
        db.flip();
        db.dialog.getStyleClass().add("reply-label"); // Style as bot reply
        return db;
    }

    /**
     * Creates a dialog box for error messages from Shrimp (the chatbot).
     * This method flips the layout so the image appears on the left, and adds a distinct error style.
     *
     * @param messageText The error message to be displayed.
     * @param shrimpImage The image representing Shrimp (the chatbot).
     * @return A flipped {@code DialogBox} styled for error messages.
     */
    public static DialogBox getErrorDialog(String messageText, Image shrimpImage) {
        var db = new DialogBox(messageText, shrimpImage);
        db.flip();
        db.dialog.getStyleClass().add("error-label"); // Style as error message
        return db;
    }
}
