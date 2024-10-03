import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import muller.Muller;

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

    private Muller muller;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image mullerImage = new Image(this.getClass().getResourceAsStream("/images/DaMuller.png"));

    /**
     * Starts of the program with greetings and a GUI.
     */
    @FXML
    public void initialize() {
        String welcomeMessage = "Hello! I'm Muller!\nWhat can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getMullerDialog(welcomeMessage, mullerImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Muller instance */
    public void setMuller(Muller m) {
        muller = m;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = muller.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMullerDialog(response, mullerImage)
        );
        userInput.clear();
        if (response.trim().equalsIgnoreCase("Bye. Hope to see you again soon!")) {
            // Create a delay before closing the application
            PauseTransition delay = new PauseTransition(Duration.seconds(2)); // 2 seconds delay
            delay.setOnFinished(event -> Platform.exit()); // Exit the application after the delay
            delay.play(); // Start the delay
        }
    }
}


