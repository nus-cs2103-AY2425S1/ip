package chatkaki;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

/**
 * Represents a cell in the chat list view.
 */
class MessageCell extends ListCell<Message> {
    private HBox content;
    private Label messageLabel;
    private ImageView profileImageView;
    private StackPane messageBubble;

    public MessageCell() {
        super();
        messageLabel = new Label();
        messageLabel.setWrapText(true);
        messageLabel.setMaxWidth(300); // Adjust based on your preference

        profileImageView = new ImageView();
        profileImageView.setFitWidth(70);
        profileImageView.setFitHeight(70);
        profileImageView.setClip(new Circle(35, 35, 35));

        messageBubble = new StackPane(messageLabel);

        content = new HBox();
        content.setSpacing(10);
    }

    @Override
    protected void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);

        if (empty || message == null) {
            setGraphic(null);
        } else {
            messageLabel.setText(message.getText());
            profileImageView.setImage(message.getProfileImage());

            // Clear existing styles
            messageBubble.getStyleClass().clear();
            messageLabel.getStyleClass().clear();
            content.getChildren().clear();

            if (message.isUser()) {
                // Style for user messages
                getStyleClass().add("user-message-row");
                messageBubble.getStyleClass().add("user-message-bubble");
                messageLabel.getStyleClass().add("user-message-text");
                content.setAlignment(Pos.TOP_RIGHT);
                content.getChildren().addAll(messageBubble, profileImageView);
            } else {
                // Style for bot messages
                getStyleClass().add("bot-message-row");
                messageBubble.getStyleClass().add("bot-message-bubble");
                messageLabel.getStyleClass().add("bot-message-text");
                content.setAlignment(Pos.TOP_LEFT);
                content.getChildren().addAll(profileImageView, messageBubble);
            }

            setGraphic(content);
        }
    }
}
