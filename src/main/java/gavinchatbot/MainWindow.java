package gavinchatbot;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

/**
 * The MainWindow class provides the main UI layout for the GavinChatBot application.
 * It handles user input and displays dialogs in the chat interface.
 */
public class MainWindow {

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField userInput;

    @FXML
    private Button sendButton;

    private GavinChatBot duke;
    private Image userImage;
    private Image dukeImage;

    /**
     * Initializes the main window by setting up the initial welcome dialog.
     * This method is called automatically after the FXML file has been loaded.
     */
    @FXML
    public void initialize() {
        // Ensure that images are set before usage
        assert userImage != null : "User image must be initialized";
        assert dukeImage != null : "Duke image must be initialized";

        // Welcome message from Duke
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog("Welcome to GavinChatBot!", dukeImage)
        );

        // Set event handlers for user input
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
    }

    /**
     * Sets the GavinChatBot instance to be used by this window.
     *
     * @param d the GavinChatBot instance
     */
    public void setDuke(GavinChatBot d) {
        duke = d;
    }

    /**
     * Sets the user and duke images to be used in the dialog boxes.
     *
     * @param userImage The image of the user.
     * @param dukeImage The image of Duke.
     */
    public void setImages(Image userImage, Image dukeImage) {
        this.userImage = userImage;
        this.dukeImage = dukeImage;
    }

    /**
     * Handles user input by processing it and displaying the corresponding dialog.
     * This method is triggered when the user clicks the send button or presses Enter.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.trim().isEmpty()) {
            return;
        }
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
