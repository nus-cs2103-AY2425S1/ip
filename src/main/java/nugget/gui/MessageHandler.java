package nugget.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A utility class responsible for handling the creation of message boxes in a chat interface.
 * It generates a formatted message box for both user and bot messages, with appropriate styling
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
     * @return A HBox containing the message and the appropriate profile image, styled and aligned based on the sender.
     */
    public static HBox createMessageBox(String message, boolean isUser, Image userImage, Image botImage) {
        HBox messageBox = createHbox();
        Label messageLabel = createMessageLabel(message);

        ImageView profileImage = createProfileImage(isUser, userImage, botImage);

        if (isUser) {
            setUserMessageAlignment(messageBox, messageLabel, profileImage);
        } else {
            setBotMessageAlignment(messageBox, messageLabel, profileImage);
        }

        return messageBox;
    }

    private static HBox createHbox() {
        HBox messageBox = new HBox(10);
        messageBox.setPadding(new Insets(5));
        return messageBox;
    }

    private static Label createMessageLabel(String message) {
        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(350);
        messageLabel.setPadding(new Insets(10));
        return messageLabel;
    }

    private static ImageView createProfileImage(boolean isUser, Image userImage, Image botImage) {
        ImageView profileImage = new ImageView(isUser ? userImage : botImage);
        profileImage.setFitWidth(40);
        profileImage.setFitHeight(40);
        return profileImage;
    }

    private static void setUserMessageAlignment(HBox messageBox, Label messageLabel, ImageView profileImage) {
        messageBox.setAlignment(Pos.CENTER_RIGHT);
        messageBox.getChildren().addAll(messageLabel, profileImage);
        messageLabel.setStyle("-fx-background-color: lightblue; -fx-background-radius: 10;");
    }

    private static void setBotMessageAlignment(HBox messageBox, Label messageLabel, ImageView profileImage) {
        messageBox.setAlignment(Pos.CENTER_LEFT);
        messageBox.getChildren().addAll(profileImage, messageLabel);
        messageLabel.setStyle("-fx-background-color: lightgreen; -fx-background-radius: 10;");
    }
}
