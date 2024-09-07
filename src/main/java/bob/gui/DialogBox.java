package bob.gui;

import java.io.IOException;
import java.util.Collections;

import bob.Bob;
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
 * Custom control representing a dialog box in the GUI.
 * This class is responsible for displaying user and bot dialogs.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox with the specified text and image.
     *
     * @param text The message to display in the dialog box.
     * @param img The image to display in the dialog box.
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
     * Changes the style of the dialog box based on the command type.
     * Color or style of the dialog box is customised for the various command types for better user experience.
     *
     * @param commandType The type of the command.
     */
    private void changeDialogStyle(Bob.Command commandType) {
        switch(commandType) {
        case TODO:
            // Fallthrough
        case DEADLINE:
            // Fallthrough
        case EVENT:
            dialog.getStyleClass().add("add-label");
            break;
        case MARK:
        case UNMARK:
            // Fallthrough
            dialog.getStyleClass().add("marked-label");
            break;
        case DELETE:
            dialog.getStyleClass().add("delete-label");
            break;
        case UNKNOWN:
            // Fallthrough
        default:
            // Do nothing
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
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates a dialog box for the user's input.
     *
     * @param text The user's input.
     * @param img The user's display picture.
     * @return A DialogBox containing the user's input and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    public static DialogBox getDukeDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }

    /**
     * Creates a dialog box for Bob's response and flips the dialog box to align with Bob's image.
     *
     * @param text Bob's response.
     * @param img Bob's display picture.
     * @return A flipped DialogBox containing Bob's message and image.
     */
    public static DialogBox getBobDialog(String text, Image img, Bob.Command commandType) {
        var db = new DialogBox(text, img);
        db.flip();
        db.changeDialogStyle(commandType);
        return db;
    }
}

