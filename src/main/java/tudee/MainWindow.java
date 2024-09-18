package tudee;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
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

    private Tudee tudee;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image tudeeImage = new Image(this.getClass().getResourceAsStream("/images/Tudee.png"));

    @FXML
    public void initialize() {
        showWelcome();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Tudee instance */
    public void setTudee(Tudee t) {
        tudee = t;
    }

    /**
     * Displays the welcome message.
     */
    private void showWelcome() {
        String welcomeMessage = "Hello! I'm Tudee, your chatbot bestie! How can I help you today? :)";
        dialogContainer.getChildren().add(DialogBox.getTudeeDialog(welcomeMessage, tudeeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Tudee's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = tudee.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTudeeDialog(response, tudeeImage)
        );
        userInput.clear();
        if (input.trim().equalsIgnoreCase("bye")) {
            // Create a pause transition to close the application after 3 seconds
            PauseTransition pause = new PauseTransition(Duration.seconds(3));
            pause.setOnFinished(event -> {
                // Close the application
                javafx.application.Platform.exit();
            });
            pause.play();
        }
    }
}
