package ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.ChatBotLogic;

/**
 * The {@code ChatBotController} class is responsible for handling the user interface interactions
 * and integrating the chatbot's logic with the JavaFX UI components.
 * It processes user input, displays messages in the chat area, and manages communication with the chatbot logic.
 */
public class ChatBotController {

	// FXML fields linked to UI elements
	@FXML
	private VBox chatArea;
	@FXML
	private TextField inputField;

	// Instance of the chatbot logic
	private ChatBotLogic chatBotLogic;

	/**
	 * Initializes the controller and sets up the chatbot logic.
	 * This method is called automatically when the FXML file is loaded.
	 */
	@FXML
	public void initialize() {
		chatBotLogic = new ChatBotLogic();  // Initialize the chatbot logic
	}

	/**
	 * Handles the send button action by capturing user input, sending it to the chatbot logic,
	 * and displaying the user message and bot response in the chat area.
	 */
	@FXML
	private void handleSend() {
		String userMessage = inputField.getText().trim();  // Get the user's message
		if (!userMessage.isEmpty()) {
			// Display user message in the chat area
			addChatMessage(userMessage, true);

			// Process the user's message and get the bot's response
			String botResponse = chatBotLogic.readInput(userMessage);

			// Display the bot's response in the chat area
			addChatMessage(botResponse, false);

			// Clear the input field after sending
			inputField.clear();
		}
	}

	/**
	 * Adds a message bubble to the chat area.
	 * The message bubble appears on the right for user messages and on the left for bot responses.
	 *
	 * @param message The message content.
	 * @param isUser  True if it's a user's message, false for the bot's message.
	 */
	private void addChatMessage(String message, boolean isUser) {
		HBox messageBox = new HBox(10);  // Create a horizontal box with spacing
		// Load the appropriate profile image based on whether the message is from the user or bot
		ImageView pfp = new ImageView(new Image(getClass().getResourceAsStream(isUser ? "/ui/user.jpg" : "/ui/sigmaOpp.jpg")));
		pfp.setFitWidth(80);
		pfp.setFitHeight(80);

		// Create a label to display the message
		Label messageLabel = new Label(message);
		messageLabel.setWrapText(true);
		messageLabel.setMaxWidth(250);  // Set maximum width for the message label
		// Set the background color and padding for the message bubble
		messageLabel.setStyle("-fx-background-radius: 15px; -fx-padding: 10; -fx-background-color: "
				+ (isUser ? "#DCF8C6;" : "#E3E3E3;"));

		// Align the message box to the right for user messages, and left for bot responses
		if (isUser) {
			messageBox.setStyle("-fx-alignment: top-right;");
			messageBox.getChildren().addAll(messageLabel, pfp);  // Add label and profile image to the box
		} else {
			messageBox.setStyle("-fx-alignment: top-left;");
			messageBox.getChildren().addAll(pfp, messageLabel);  // Add profile image and label to the box
		}
		// Add the message box to the chat area (VBox)
		chatArea.getChildren().add(messageBox);
	}
}
