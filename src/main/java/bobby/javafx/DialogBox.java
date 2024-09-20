package bobby.javafx;

import java.io.IOException;
import java.util.Collections;

import bobby.MainWindow;
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
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates a DialogBox with the given text and image.
     * The constructor loads the associated FXML file to set up the dialog box
     * layout, sets the text and image for the dialog, and applies styling to
     * the ImageView.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be shown as the display picture.
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

        applyImageStyle();
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
     * Applies styling to the ImageView by setting its size, ensuring smooth rendering,
     * and clipping it into a circular shape. This enhances the visual appearance of the
     * display picture.
     */
    private void applyImageStyle() {
        // Increase the size to ensure the full image is visible
        double size = 120.0;
        displayPicture.setFitWidth(size);
        displayPicture.setFitHeight(size);

        // Center the image within the ImageView
        displayPicture.setPreserveRatio(true);
        displayPicture.setSmooth(true);
        displayPicture.setCache(true);

        // Apply circular clipping
        Circle clip = new Circle(size / 2, size / 2, size / 2);
        displayPicture.setClip(clip);

        // Ensure the HBox gives enough space to the ImageView
        setMinHeight(size);
        setPrefHeight(size);
    }

    /**
     * Creates and returns a DialogBox representing a user's message.
     *
     * @param text The message text.
     * @param img The user's display picture.
     * @return A DialogBox containing the user's message and display picture.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        return db;

    }

    /**
     * Creates and returns a DialogBox representing Bobby's message.
     * The dialog box is flipped so that the display picture is on the left.
     *
     * @param text The message text.
     * @param img Bobby's display picture.
     * @return A flipped DialogBox containing Bobby's message and display picture.
     */
    public static DialogBox getBobbyDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
