package ipman.ui.javafx.components;

import java.io.IOException;
import java.io.InputStream;

import ipman.ui.javafx.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Represents the JavaFx Node for containing a message from either the user or the chatbot
 */
public class MessageBox extends GridPane {
    @FXML
    private ImageView displayPicture;
    @FXML
    private Label message;

    private MessageBox(String message, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MessageBox.class.getResource("/components/MessageBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.message.setText(message);
        this.displayPicture.setImage(img);
    }

    private static Image loadImage(String resourcePath) {
        InputStream is = MessageBox.class.getResourceAsStream(resourcePath);
        assert is != null;
        return new Image(is);
    }

    /**
     * Constructs a MessageBox for a user or system depending on the message's author
     *
     * @param message the message to represent
     * @return message box for the user or system
     */
    public static MessageBox createMessage(Message message) {
        return switch (message.author()) {
        case USER -> createUserBox(message.message());
        case SYSTEM -> createSystemBox(message.message());
        };
    }

    /**
     * Constructs a MessageBox representing the user's message
     *
     * @param message the message to contain
     * @return message box for the user
     */
    public static MessageBox createUserBox(String message) {
        Image image = loadImage("/images/messageUserAvatar.png");
        MessageBox box = new MessageBox(message, image);
        box.message.setAlignment(Pos.CENTER_RIGHT);
        return box;
    }

    /**
     * Constructs a MessageBox representing the system's message
     *
     * @param message the message to contain
     * @return message box for the system
     */
    public static MessageBox createSystemBox(String message) {
        Image image = loadImage("/images/messageSystemAvatar.png");
        MessageBox box = new MessageBox(message, image);
        GridPane.setColumnIndex(box.message, 1);
        GridPane.setColumnIndex(box.displayPicture, 0);
        return box;
    }
}
