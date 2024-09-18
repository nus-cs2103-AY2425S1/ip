package seedu.maxine;

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

    private Maxine maxine;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaMaxine.png"));

    /**
     * Initialises the dialog container
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        String initialPrompt = "What can I do for you today?";
        dialogContainer.getChildren().add(
                DialogBox.getMaxineDialog(initialPrompt, dukeImage)
        );
    }

    /** Injects the Maxine instance */
    public void setMaxine(Maxine m) {
        maxine = m;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Maxine.
     * Maxine's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("bye")) {
            String response = maxine.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getMaxineDialog(response, dukeImage)
            );
            userInput.clear();
            userInput.setDisable(true); // Disable the input field
            return; // Exit the method early
        }
        String response = maxine.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getMaxineDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
