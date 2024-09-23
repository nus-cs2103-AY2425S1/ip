package pebble;

import javafx.fxml.FXML;
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

    private Pebble pebble;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image pebbleImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the main window and greets the user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        this.showWelcome();
    }

    /**
     * Shows the welcome message when the application is started.
     */
    public void showWelcome() {
        dialogContainer.getChildren().addAll(
                DialogBox.getPebbleDialog("Hello! I'm Pebble, your personal chatbot."
                        + " How can I help you today?", pebbleImage)
        );
    }

    /**
     * Injects the Pebble instance
     */
    public void setPebble(Pebble p) {
        assert p != null : "Pebble object cannot be null";
        pebble = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "User input should not be null";
        String response = pebble.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPebbleDialog(response, pebbleImage)
        );
        userInput.clear();
    }
}
