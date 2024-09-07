package nugget.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MessageHandler {

    public static HBox createMessageBox(String message, boolean isUser, Image userImage, Image botImage) {
        HBox messageBox = new HBox(10);
        messageBox.setPadding(new Insets(5));

        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(350);
        messageLabel.setPadding(new Insets(10));  // Add padding inside the label

        ImageView profileImage = new ImageView(isUser ? userImage : botImage);
        profileImage.setFitWidth(40);
        profileImage.setFitHeight(40);

        if (isUser) {
            messageBox.setAlignment(Pos.CENTER_RIGHT);
            messageBox.getChildren().addAll(messageLabel, profileImage);
            messageLabel.setStyle("-fx-background-color: lightblue; -fx-background-radius: 10;");
        } else {
            messageBox.setAlignment(Pos.CENTER_LEFT);
            messageBox.getChildren().addAll(profileImage, messageLabel);
            messageLabel.setStyle("-fx-background-color: lightgreen; -fx-background-radius: 10;");
        }

        return messageBox;
    }
}

