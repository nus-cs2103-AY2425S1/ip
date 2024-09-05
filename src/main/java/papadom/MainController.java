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

    @FXML
    private VBox chatArea;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField inputField;

    private Papadom chatbot;

    public MainController() {
        chatbot = new Papadom(); // Initialize the Papadom chatbot
    }

    @FXML
    public void initialize() {
        addMessage(Ui.welcomeMessage(), Pos.CENTER_LEFT, "#FFFFFF"); // Display welcome message on the left
        // Attach a listener to the chat area to scroll down automatically when new messages are added
        chatArea.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(1.0); // Scroll to the bottom
        });
    }

    @FXML
    private void handleSendAction() {
        String userInput = inputField.getText().trim();
        if (!userInput.isEmpty()) {
            addMessage(userInput, Pos.CENTER_RIGHT, "#DCF8C6"); // User message on the right

            // Get the response from the chatbot
            String response = Papadom.getResponse(userInput);
            addMessage(response, Pos.CENTER_LEFT, "#FFFFFF"); // Bot message on the left

            // Clear the input field after sending
            inputField.clear();

            // If the command is "bye", you might want to close the application or handle it differently
            if (userInput.equalsIgnoreCase("bye")) {
                addMessage("Session ended. Goodbye!", Pos.CENTER_LEFT, "#FFFFFF");
                inputField.setDisable(true); // Disable further input
            }
        }
    }

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
