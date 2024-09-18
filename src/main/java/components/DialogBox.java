package components;

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

import java.io.IOException;
import java.util.Collections;

/**
 * The DialogBox class represents a custom control for displaying dialog messages in a chat-like UI.
 * It contains a message and a profile picture, allowing for user and chatbot messages to be displayed.
 */
public class DialogBox extends HBox {
    @FXML
    private ImageView profilePic;
    @FXML
    private Label message;

    /**
     * Constructs a DialogBox with the specified message and profile picture.
     *
     * @param message The message to be displayed.
     * @param profilePic The profile picture to be displayed.
     */
    private DialogBox(String message, Image profilePic) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(DialogBox.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
            this.message.setText(message);
            this.profilePic.setImage(profilePic);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the DialogBox by setting up the clipping of the profile picture to be circular.
     */
    @FXML
    private void initialize() {
        Circle clip = new Circle(25,25,25);
        profilePic.setClip(clip);
    }

    /**
     * Flips the dialog box such that the profile picture and the message are swapped.
     * Used to differentiate between user and chatbot messages visually.
     */
    private void flip() {
        assert !this.getChildren().isEmpty() : "Every dialog box must have children";
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        this.getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Creates a DialogBox for the user message.
     *
     * @param userMessage The user's message.
     * @param profilePic The user's profile picture.
     * @return A DialogBox displaying the user's message.
     */
    public static DialogBox getUserDialog(String userMessage, Image profilePic) {
        return new DialogBox(userMessage, profilePic);
    }

    /**
     * Creates a DialogBox for the Blitz chatbot response.
     * The dialog box is flipped to differentiate it from user messages.
     *
     * @param blitzResponse The Blitz chatbot's response message.
     * @param profilePic The Blitz chatbot's profile picture.
     * @return A DialogBox displaying the Blitz chatbot's response.
     */
    public static DialogBox getBlitzDialog(String blitzResponse, Image profilePic) {
        DialogBox dukeDialogBox = new DialogBox(blitzResponse, profilePic);
        dukeDialogBox.flip();
        return dukeDialogBox;
    }
}


