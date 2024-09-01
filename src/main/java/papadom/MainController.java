package papadom;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import papadom.utils.Papadom;


public class MainController {

    @FXML
    private TextArea chatArea;

    @FXML
    private TextField inputField;

    private Papadom chatbot; // Reference to your bot

    public MainController() {
        // Initialize the bot
        chatbot = new Papadom(); // Ensure Papadom class is instantiated here
    }

    @FXML
    private void handleSendAction() {
        String userInput = inputField.getText().trim();
        if (!userInput.isEmpty()) {
            appendMessage("You: " + userInput);

            // Get the response from the bot
            String response = chatbot.getResponse(userInput); // Ensure this method exists in Papadom class
            appendMessage("Bot: " + response);

            // Clear the input field after sending
            inputField.clear();
        }
    }

    private void appendMessage(String message) {
        chatArea.appendText(message + "\n");
    }
}
