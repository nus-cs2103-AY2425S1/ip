package xizi.chatbot.gui;

import java.util.Objects;

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
import xizi.chatbot.Xizi;


/**
 * Serves as the controller for the main GUI.
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

    private Xizi xizi;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/tea.png")));
    private final Image xiziImage = new Image(Objects.requireNonNull(this.getClass()
            .getResourceAsStream("/images/wine.png")));
    /**
     * Initializes the main window.
     */

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcomeMessage();
    }

    /**
     * Injects the Xizi instance
     */
    public void setXizi(Xizi bot) {
        xizi = bot;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = xizi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getXiziDialog(response, xiziImage)
        );
        // Check if response contains the Goodbye flag
        // Thread.sleep(1500) is blocking the JavaFX application thread, preventing the UI from being updated
        if (response.contains("Goodbye")) {
            // Create a pause to allow the goodbye message to be shown
            PauseTransition delay = new PauseTransition(Duration.seconds(1.0)); // 1.0-second delay
            delay.setOnFinished(event -> Platform.exit()); // Close the application after delay
            delay.play(); // Start the pause transition
        }
        userInput.clear();
    }

    /**
     * Displays a welcome message when the application is first opened.
     */
    private void showWelcomeMessage() {
        Platform.runLater(() -> { //need to run on the application thread?
            String welcomeMessage = xizi.returnWelcomeString();
            dialogContainer.getChildren().add(DialogBox.getXiziDialog(welcomeMessage, xiziImage));
        });
    }
}

