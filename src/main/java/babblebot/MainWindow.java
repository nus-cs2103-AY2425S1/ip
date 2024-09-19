package babblebot;

import babblebot.ui.Ui;
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

    private BabbleBot babbleBot;
    private boolean isFirstInteraction = true;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /** Initializes the scrollPane */
    @FXML
    public void initialize() {
        assert isFirstInteraction : "First interaction should only happen at startup";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        displayWelcomeMessage();
        isFirstInteraction = false;
    }

    /** Injects the BabbleBot instance */
    public void setBabbleBot(BabbleBot d) {
        babbleBot = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = babbleBot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (response.equals(Ui.getGoodbyeMessage())) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));  // Delay for 1 second
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }

    /**
     * Displays the welcome message on startup.
     */
    private void displayWelcomeMessage() {
        String welcomeMessage = Ui.getWelcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
    }
}
