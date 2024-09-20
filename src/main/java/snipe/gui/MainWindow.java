package snipe.gui;

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
import snipe.core.Snipe;
import snipe.exception.SnipeException;

import java.io.IOException;

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

    private Snipe snipe;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image snipeImage = new Image(this.getClass().getResourceAsStream("/images/DaSnipe.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Snipe instance */
    public void setSnipe(Snipe s) {
        this.snipe = s;
        String welcomeMessage = snipe.getWelcomeMessage();
        dialogContainer.getChildren().add(
                DialogBox.getSnipeDialog(welcomeMessage, snipeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Snipe's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException, SnipeException {
        String input = userInput.getText();
        String response = snipe.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSnipeDialog(response, snipeImage)
        );
        userInput.clear();
        if (input.trim().equalsIgnoreCase("bye")) { // Replace "bye" with your desired trigger input
            delayCloseApplication();
        }
    }

/**
 * Delays the closing of the application by 1 second to ensure that the closing message
 * is displayed to the user before the application exits. This method creates a
 * {@code PauseTransition} to introduce a short delay, providing a smoother user experience.
 *
 * <p>Once the delay is completed, the application will close using {@code Platform.exit()},
 * which exits the JavaFX application cleanly, terminating all threads and closing all windows.
 * This method is typically called when a specific user input, such as "bye", is detected.
 * </p>
 */
    private void delayCloseApplication() {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}

