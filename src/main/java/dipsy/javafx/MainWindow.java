package dipsy.javafx;

import dipsy.Dipsy;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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

    private Dipsy dipsy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/PepeClown.jpeg"));
    private Image dipsyImage = new Image(this.getClass().getResourceAsStream("/images/Dipsy.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Dipsy instance and shows welcome message. */
    public void setDipsy(Dipsy dipsy) {
        this.dipsy = dipsy;
        dipsy.setMainWindow(this);

        showWelcomeMessage();
    }

    @FXML
    private void showWelcomeMessage() {
        String welcome = dipsy.getWelcomeMessage();
        dialogContainer.getChildren().addAll(
                DialogBox.getDipsyDialog(welcome, dipsyImage)
        );
    }

    /**
     * Closes the JavaFX application with a delay.
     * This method can be called by the {@link Dipsy} class to trigger the application shutdown.
     *
     * @param delayMillis The delay in milliseconds before the application exits.
     */
    public void closeApplicationWithDelay(long delayMillis) {
        // Run the delay on a background thread
        new Thread(() -> {
            try {
                // Delay the application exit
                Thread.sleep(delayMillis);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Restore interrupted state
            }

            // Now exit the application on the JavaFX Application Thread
            Platform.runLater(Platform::exit);
        }).start();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Dipsy's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = dipsy.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDipsyDialog(response, dipsyImage)
        );
        userInput.clear();
    }
}
