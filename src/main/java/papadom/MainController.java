package papadom;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
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

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image botImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    // Constructor initializes the Papadom chatbot
    public MainController() {}

    /**
     * Initializes the chat interface.
     * This method is called automatically after the FXML file is loaded.
     * It sets up the chat area, displays the bot's welcome message, and ensures
     * that the chat scrolls to the bottom as new messages are added.
     */
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

        // Add key event listener to inputField to listen for Enter key press
        inputField.setOnKeyPressed(event -> {
            switch (event.getCode()) {
            case ENTER -> handleSendAction(); // Call the send action when Enter is pressed
            default -> System.out.println("Unhandled key pressed: " + event.getCode());
            }
        });
    }

    /**
     * Handles the action triggered when the user clicks the "Send" button.
     * The method retrieves the user's input, displays it in the chat, processes
     * it through the bot, and displays the bot's response. If the input is "bye",
     * the window is closed.
     */
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

            // If the command is "bye", disable input and close the window after 3 seconds
            if (userInput.equalsIgnoreCase(BYE)) {
                inputField.setDisable(true); // Disable input field
                PauseTransition pause = new PauseTransition(Duration.seconds(2)); // Wait for 3 seconds
                pause.setOnFinished(event -> {
                    Stage stage = (Stage) inputField.getScene().getWindow(); // Get the current window (Stage)
                    stage.close(); // Close the window after 3 seconds
                });
                pause.play(); // Start the pause timer
            }
        }
    }
}
