package noosy.gui;

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
 * This class extends HBox and is used to display messages in a GUI.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a new DialogBox with the given text and image.
     *
     * @param text The text to be displayed in the dialog box.
     * @param img The image to be displayed as the speaker's face.
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
     * This method is used to change the orientation of the dialog box.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Creates and returns a DialogBox instance for user messages.
     *
     * @param text The text of the user's message.
     * @param img The image representing the user.
     * @return A new DialogBox instance configured for user messages.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        return new DialogBox(text, img);
    }

    /**
     * Changes the style of the dialog based on the command type.
     * This method adds a specific CSS class to the dialog label depending on the command.
     *
     * @param commandType The type of command, which determines the style to be applied.
     */
    private void changeDialogStyle(String commandType) {
        switch(commandType) {
            case "AddCommand":
                dialog.getStyleClass().add("add-label");
                break;
            case "ChangeMarkCommand":
                dialog.getStyleClass().add("marked-label");
                break;
            case "DeleteCommand":
                dialog.getStyleClass().add("delete-label");
                break;
            default:
                // Do nothing
        }
    }

    /**
     * Creates and returns a DialogBox instance for Noosy messages.
     * This method creates a dialog box, flips it, and applies a specific style based on the command type.
     *
     * @param text The text of Noosy's message.
     * @param img The image representing Noosy.
     * @return A new DialogBox instance configured for Noosy messages.
     */
    public static DialogBox getNoosyDialog(String text, Image img) {
        var db = new DialogBox(text, img);
        db.flip();
        return db;
    }
}
