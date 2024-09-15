package bob;

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
 * Controller for the main GUI of the Bob chatbot application.
 * Manages user interactions and updates the dialog container with messages.
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

    private Bob bob;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image bobImage = new Image(this.getClass().getResourceAsStream("/images/DaBob.png"));

    /**
     * Initializes the controller. Binds the scroll pane's vertical value property to the dialog container's height
     * to keep the scroll pane scrolled to the bottom.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Bob instance for this controller.
     *
     * @param b The Bob instance to be set.
     */
    public void setBob(Bob b) {
        this.bob = b;
    }

    /**
     * Displays the welcome message from Bob in the dialog container.
     * This method should be called after setting the Bob instance to ensure proper initialization.
     */
    public void showWelcomeMessage() {
        if (this.bob != null && this.bob.getUi() != null) {
            displayMessage(this.bob.getUi().showWelcomeMessage("Bob"), bobImage);
        } else {
            System.err.println("Bob or Ui has not been initialized.");
        }
    }

    /**
     * Handles the user input from the text field. Displays the user input and the corresponding response from Bob in
     * the dialog container. Clears the user input field after processing.
     * If the user input is "bye", displays the goodbye message and closes the application after a short delay.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        if (userText.equals("bye")) {
            displayMessage("bye", userImage);

            displayMessage(bob.getUi().showGoodbyeMessage(), bobImage);

            // Create a PauseTransition to wait before closing the application
            PauseTransition pause = new PauseTransition(Duration.seconds(2)); // 2 seconds delay
            pause.setOnFinished(event -> {
                Platform.exit();
            });
            pause.play();
            return;
        }
        String bobText = bob.getResponse(userInput.getText());
        displayMessage(userText, userImage);
        displayMessage(bobText, bobImage);
        userInput.clear();
    }

    /**
     * Displays a message in the dialog container as a DialogBox.
     * Creates a dialog box for the user or Bob based on the provided image.
     *
     * @param message The message to display.
     * @param image The image associated with the message (user or Bob).
     */
    @FXML
    private void displayMessage(String message, Image image) {
        DialogBox dialogBox;
        if (image.equals(userImage)) {
            dialogBox = DialogBox.getUserDialog(message, image);
        } else {
            dialogBox = DialogBox.getBobDialog(message, image);
        }
        dialogContainer.getChildren().add(dialogBox);
    }
}
