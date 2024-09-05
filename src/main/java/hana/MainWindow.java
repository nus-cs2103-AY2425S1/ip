package hana;

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

    private Hana hana;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Display welcome message when the program starts
        String welcomeMessage = Ui.showWelcome();  // Get the welcome message
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(welcomeMessage, dukeImage));
    }

    /** Injects the Hana instance */
    public void setHana(Hana h) {
        hana = h;
    }

    /**
     * Creates two dialog boxes: One for user, and one for Hana.
     * Clears the user input after processing.
     * If user input is "bye", text field and send button will be disabled.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = hana.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        // Check if the user has entered "bye"
        if (input.equals("bye")) {
            userInput.clear();
            userInput.setDisable(true);  // Disable input field to stop further input
            sendButton.setDisable(true); // Disable send button as well
        } else {
            userInput.clear();  // Clear input for the next command
        }
    }
}

