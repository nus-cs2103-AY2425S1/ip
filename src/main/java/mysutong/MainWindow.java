package mysutong;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 * This class is responsible for managing user interactions in the JavaFX GUI,
 * such as handling user input and displaying responses from MySutong.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;  // ScrollPane to display the conversation between the user and MySutong.
    @FXML
    private VBox dialogContainer;   // VBox to hold dialog boxes.
    @FXML
    private TextField userInput;    // TextField for the user to type input.
    @FXML
    private Button sendButton;      // Button to submit the user input.

    private MySutong mysutong;      // Instance of MySutong to process user commands.

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));  // Image representing the user.
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));  // Image representing Duke (MySutong's responses).

    /**
     * Initializes the controller.
     * This method binds the ScrollPane's scroll value to the height of the dialog container,
     * ensuring that the latest dialog is always visible when the user interacts with the GUI.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the MySutong instance into this controller.
     * This method is used to pass the MySutong object from the main application into the controller
     * so that the controller can delegate user input processing to MySutong.
     *
     * @param m The MySutong instance to be used by this controller.
     */
    public void setMySutong(MySutong m) {
        mysutong = m;
    }

    /**
     * Handles user input by creating dialog boxes for both the user input and MySutong's response.
     * This method is triggered when the user submits a command. It processes the command, gets the response from
     * MySutong, and displays both the input and response as dialog boxes in the dialog container. The user's input
     * field is then cleared.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = mysutong.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
