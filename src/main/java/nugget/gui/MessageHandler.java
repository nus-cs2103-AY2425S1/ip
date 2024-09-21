package nugget.gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A utility class responsible for handling the creation of message boxes in a chat interface.
 * It loads FXML-based message boxes for both user and bot messages, with appropriate styling
 * and positioning within the chat window.
 */
public class MessageHandler {

    /**
     * Creates a message box containing a text message and a profile image.
     *
     * @param message The text message to be displayed.
     * @param isUser A boolean indicating if the message is from the user (true) or the bot (false).
     * @param userImage The profile image of the user.
     * @param botImage The profile image of the bot.
     * @return A HBox containing the message and the appropriate profile image, loaded from the FXML file.
     */
    public static HBox createMessageBox(String message, boolean isUser, Image userImage, Image botImage) {
        HBox messageBox;
        FXMLLoader loader;

        if (isUser) {
            loader = new FXMLLoader(MessageHandler.class.getResource("/view/UserMessageBox.fxml"));
        } else {
            loader = new FXMLLoader(MessageHandler.class.getResource("/view/BotMessageBox.fxml"));
        }

        try {
            messageBox = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return new HBox(); // Return an empty HBox on failure
        }

        Label messageLabel = (Label) messageBox.lookup("#messageLabel");
        ImageView profileImage = (ImageView) messageBox.lookup("#profileImage");

        messageLabel.setText(message);
        profileImage.setImage(isUser ? userImage : botImage);

        return messageBox;
    }
}
