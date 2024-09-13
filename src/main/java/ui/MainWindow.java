package ui;

import buddy.Buddy;
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

    private Buddy buddy;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaGoat.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setBuddy(Buddy b) {
        this.buddy = b;

        // Now that Buddy is set, show the welcome message
        String welcomeMessage = buddy.runStartupMsg();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcomeMessage, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    @FXML
    private void handleUserInput() {
        if (buddy.isExit()) {
            // Show the exit message
            String exitMessage = buddy.runExitMsg();
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(exitMessage, dukeImage)
            );

            // Disable the text input and send button to prevent further input
            userInput.setDisable(true);
            sendButton.setDisable(true);
            return;
        }

        String input = userInput.getText();
        String response = buddy.getResponse(input);
        assert response != null : "response should not be null";
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();
    }
}
