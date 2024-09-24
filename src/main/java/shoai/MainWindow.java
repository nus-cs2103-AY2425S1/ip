package shoai;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.animation.PauseTransition; // Add this import at the top of your class
import javafx.util.Duration; // Add this import at the top of your class

/**
 * Represents the main window of the ShoAI application, handling user interactions
 * and displaying dialogs.
 */
public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private ShoAI chatbot;
    private boolean hasBeenWelcomed;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image chatbotImage = new Image(this.getClass().getResourceAsStream("/images/shoai.jpeg"));

    /**
     * Initializes the MainWindow by setting up the ScrollPane to automatically scroll
     * to the bottom and showing the welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }

    /**
     * Sets the ShoAI chatbot instance for handling user input and generating responses.
     *
     * @param c The ShoAI chatbot instance to be set.
     */
    public void setChatbot(ShoAI c) {
        chatbot = c;
    }

    /**
     * Handles user input by processing it through the chatbot and displaying the
     * resulting response in the dialog container. It also checks for error messages
     * and displays them appropriately.
     */
    @FXML
    private void handleUserInput() {
        if (!hasBeenWelcomed) {
            showWelcomeMessage();
            hasBeenWelcomed = true;
        }
        String input = userInput.getText();
        String response = chatbot.getResponse(input);

        // Check if the response is an error by some criteria
        if (response.contains("Error") || response.contains("invalid")) { // Example check
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getErrorDialog(response, chatbotImage) // Error dialog in red
            );
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getChatbotDialog(response, chatbotImage)
            );
            if (response.equals("So long, partner!")) {
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(event ->  {
                    System.exit(0);
                });
                pause.play();
            }
        }
        userInput.clear();
    }

    /**
     * Displays a welcome message from the chatbot in the dialog container if it
     * has not already been displayed.
     */
    private void showWelcomeMessage() {
        if (!hasBeenWelcomed) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getChatbotDialog("What's up! \uD83D\uDC4B I'm ShoAI, what do you need today?",
                            chatbotImage)
            );
            hasBeenWelcomed = true;
        }
    }
}
