package stan.ui;

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
import stan.Stan;

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

    private Stan stan;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image stanImage = new Image(this.getClass().getResourceAsStream("/images/Stan.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Stan instance */
    public void setStan(Stan stan) {
        this.stan = stan;
        // Add the welcome message when the app starts
        dialogContainer.getChildren().add(DialogBox.getStanDialog(stan.getWelcomeMessage(), stanImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Stan's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = stan.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getStanDialog(response, stanImage)
        );
        userInput.clear();

        // Check if the user entered "bye" to exit
        if (stan.isExitCommand(input)) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
}
