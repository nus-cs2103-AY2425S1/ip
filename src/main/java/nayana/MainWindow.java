package nayana;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI of the application.
 * Manages the interaction between user input and the display of dialog boxes.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane; // The scroll pane that allows scrolling through the dialog container.
    @FXML
    private VBox dialogContainer; // The container for holding dialog boxes.
    @FXML
    private TextField userInput; // The text field for user input.
    @FXML
    private Button sendButton; // The button to send user input.

    private Nayana nayana; // Instance of the Nayana class for processing commands.

    // Image for user dialog.
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));

    // Image for Nayana dialog.
    private Image nayanaImage = new Image(this.getClass().getResourceAsStream("/images/Nayana.png"));

    /**
     * Initializes the controller after its root element has been processed.
     * Binds the scroll pane's vertical value to the height property of the dialog container for automatic scrolling.
     */
    @FXML
    public void initialize() {
        // Ensures scroll pane scrolls as dialog container grows.
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Nayana instance into the controller.
     * Sets up the UI for interaction and displays a welcome message.
     *
     * @param n The Nayana instance to be used by this controller.
     */
    public void setNayana(Nayana n) {
        nayana = n;
        nayana.getUi().setVbox(dialogContainer); // Sets the dialog container in the Nayana UI.
        nayana.getUi().showWelcomeMessage(); // Displays a welcome message.
    }

    /**
     * Handles user input by creating and adding dialog boxes to the dialog container.
     * Processes the user input and clears the text field after handling.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText(); // Retrieves user input from the text field.
        dialogContainer.getChildren().addAll(
              DialogBox.getUserDialog(input, userImage) // Adds user dialog box to the container.
        );
        nayana.parseCommand(input); // Passes the user input to Nayana for command processing.
        userInput.clear(); // Clears the text field after processing the input.
    }
}
