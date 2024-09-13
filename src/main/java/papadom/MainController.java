package papadom;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import papadom.utils.Papadom;
import papadom.utils.Ui;


public class MainController {

    public static final String BYE = "bye";

    @FXML
    private VBox chatArea;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private TextField inputField;

        private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        private Image botImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

        // Constructor initializes the Papadom chatbot
    public MainController() {}

    // Called automatically when the controller is initialized (after FXML is loaded)
    @FXML
    public void initialize() {
        // Display bot's welcome message (aligned to the left)
        DialogBox botDialog = new DialogBox(Ui.welcomeMessage(), botImage);
        botDialog.flip(); // Flip bot's message to the left (text on the right, image on the left)
        chatArea.getChildren().add(botDialog);

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
            // Add user's message (text on the left, image on the right)
            DialogBox userDialog = new DialogBox(userInput, userImage);
            chatArea.getChildren().add(userDialog);

            // Get bot's response and display (text on the right, image on the left)
            String response = Papadom.getResponse(userInput);
            DialogBox botDialog = new DialogBox(response, botImage);
            botDialog.flip(); // Flip bot's dialog to align on the left
            chatArea.getChildren().add(botDialog);

            // Clear the input field after sending
            inputField.clear();

            // If the command is "bye", disable further input
            if (userInput.equalsIgnoreCase(BYE)) {
                inputField.setDisable(true); // Disable further input
            }
        }
    }
}
