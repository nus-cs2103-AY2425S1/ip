package tohru.ui;

import java.io.IOException;

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
 * Represents a chatbot message in the user interface.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    /**
     * Creates an DialogBox instance.
     *
     * @param text The text to be displayed.
     * @param img The image to identify the message sender.
     */
    public DialogBox(String text, Image img) {
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
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
        dialog.getStyleClass().add("reply-label");
    }

    private void tintReply(boolean isError) {
        if (isError) {
            dialog.getStyleClass().add("error-label");
        } else {
            dialog.getStyleClass().add("success-label");
        }
    }

    /**
     * Represents a chat message sent by the user.
     *
     * @param message Message written by user.
     * @return A DialogBox instance with the user's profile picture.
     */
    public static DialogBox getUserDialog(String message) {
        Image userImage = new Image(DialogBox.class.getResourceAsStream("/images/Kobayashi.jpg"));
        return new DialogBox(message, userImage);
    }

    /**
     * Represents a chat message sent by the chatbot.
     *
     * @param message Message written by chatbot.
     * @return A DialogBox instance with the chatbot's profile picture.
     */
    public static DialogBox getChatbotDialog(String message, boolean isError) {
        Image chatbotImage = new Image(DialogBox.class.getResourceAsStream("/images/Tohru.jpg"));
        var db = new DialogBox(message, chatbotImage);
        db.flip();
        db.tintReply(isError);
        return db;
    }

}

