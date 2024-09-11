package fishman.gui;

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
 * Represents a chat message in the user interface.
 */
public class DialogBox extends HBox {

    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Constructs a DialogBox instance.
     *
     * @param message The text to be displayed.
     * @param img The image to be displayed which determines the user.
     */
    private DialogBox(String message, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(message);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> childNodes = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(childNodes);
        getChildren().setAll(childNodes);
        setAlignment(Pos.TOP_LEFT);
        dialog.getStyleClass().add("reply-label");
    }

    /**
     * Represents a user's chat message.
     *
     * @param message The message sent by the user.
     * @param userImage The image which represents the user.
     * @return A DialogBox instance with the user's image.
     */
    public static DialogBox getUserDialog(String message, Image userImage) {
        return new DialogBox(message, userImage);
    }

    /**
     * Represents the chatbots chat message.
     * @param message The message sent by the chatbot.
     * @param fishmanImage The image which represents the chatbot.
     * @return A DialogBox instance with the chatbot image.
     */
    public static DialogBox getFishmanDialog(String message, Image fishmanImage) {
        var db = new DialogBox(message, fishmanImage);
        db.flip();
        return db;
    }
}
