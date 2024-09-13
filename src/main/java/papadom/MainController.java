package papadom;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import papadom.utils.Papadom;
import papadom.utils.Ui;




public class MainController {

    public static final String USER_MESSAGE_COLOR = "#DCF8C6";
    public static final String BOT_MESSAGE_COLOR = "#FFFFFF";
    public static final String BYE = "bye";
    @FXML
    private VBox chatArea;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField inputField;

    private Papadom chatbot;

    // Constructor initializes the Papadom chatbot
    public MainController() {
        chatbot = new Papadom(); // Initialize the Papadom chatbot
    }

    // Called automatically when the controller is initialized (after FXML is loaded)
    @FXML
    public void initialize() {
        // Display welcome message on the left
        addMessage(Ui.welcomeMessage(), Pos.CENTER_LEFT, BOT_MESSAGE_COLOR);

        // Scroll to the bottom whenever the chat area changes its height (new messages added)
        chatArea.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(1.0); // Scroll to the bottom
        });
    }

    // Called when the user clicks the "Send" button
    @FXML
    private void handleSendAction() {
        String userInput = inputField.getText().trim();
        if (!userInput.isEmpty()) {
            // Add the user's message to the chat area (on the right side)
            addMessage(userInput, Pos.CENTER_RIGHT, USER_MESSAGE_COLOR); // User message on the right

            // Get the chatbot's response and add it to the chat area (on the left side)
            String response = Papadom.getResponse(userInput);
            addMessage(response, Pos.CENTER_LEFT, BOT_MESSAGE_COLOR); // Bot message on the left

            // Clear the input field after sending
            inputField.clear();

            // If the command is "bye", disable further input
            if (userInput.equalsIgnoreCase(BYE)) {
                inputField.setDisable(true); // Disable further input
            }
        }
    }

    // Adds a message (either user or bot) to the chat area
    private void addMessage(String message, Pos alignment, String backgroundColor) {
        Label messageLabel = new Label(message);
        messageLabel.setWrapText(true);
        messageLabel.setMinHeight(Region.USE_PREF_SIZE); // Allow label to grow vertically
        messageLabel.setStyle("-fx-background-color: " + backgroundColor + "; -fx-padding: 10; -fx-border-radius: 5; -fx-background-radius: 5;");

        HBox messageBox = new HBox();
        messageBox.setAlignment(alignment);
        messageBox.getChildren().add(messageLabel);

        chatArea.getChildren().add(messageBox);
    }
}
