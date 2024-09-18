package agave.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.shape.Circle;

import java.util.Collections;

/**
 * A custom control using FXML that represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;

    @FXML
    private ImageView displayPicture;

    /**
     * Creates a DialogBox with the given text and image.
     * @param text The text to display in the dialog box.
     * @param image The image to display in the dialog box.
     */
    private DialogBox(String text, Image image) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(image);

        displayPicture.setFitHeight(50);
        displayPicture.setFitWidth(50);
        displayPicture.setPreserveRatio(true);
        displayPicture.setClip(new Circle(25, 25, 25));
    }

    /**
     * Creates a DialogBox for error messages.
     * @param response The error message.
     * @param agaveImage The Agave image.
     * @return A DialogBox with the error message.
     */
    public static DialogBox getErrorDialog(String response, Image agaveImage) {
        DialogBox db = new DialogBox(response, agaveImage);
        db.flip();
        db.getStyleClass().add("error-dialog");
        return db;
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
     * Creates a DialogBox for the user's messages (right-aligned).
     * @param text The user's message.
     * @param img The user's profile image.
     * @return A DialogBox with the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.setAlignment(Pos.TOP_RIGHT);
        return db;
    }

    /**
     * Creates a DialogBox for the bot's messages (left-aligned).
     * @param text The bot's message.
     * @param img The bot's profile image.
     * @return A DialogBox with the bot's message.
     */
    public static DialogBox getBotDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
