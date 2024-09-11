package ui;

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
     * Constructs an ui.DialogBox with the specified text and image.
     * The layout is loaded from an FXML file, and the text and image are displayed.
     * Optionally flips the dialog layout to switch text and image positions.
     *
     * @param text The text to be displayed in the dialog.
     * @param img The image to be displayed alongside the text.
     * @param flip If true, the dialog box is flipped so that the image is on the left.
     */
    private DialogBox(String text, Image img, boolean flip) {
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

        if (flip) {
            flip();
        }
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
     * Creates a dialog box for the user with their profile image and message.
     *
     * @param text The user's input message.
     * @param img The user's profile image.
     * @return An ui.DialogBox representing the user's message.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img, false);
    }

    /**
     * Creates a dialog box for the bot with its profile image and response.
     *
     * @param text The bots' response message.
     * @param img The bots' profile image.
     * @return An ui.DialogBox representing the bots' response.
     */
    public static DialogBox getBotDialog(String text, Image img) {
        return new DialogBox(text, img, true);
    }
}
