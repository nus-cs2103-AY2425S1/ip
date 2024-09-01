package gui;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import regina.Regina;

/**
 * Controller for the main GUI of the Regina chatbot application.
 * This class handles user input, manages the dialog container,
 * and integrates with the Regina backend to retrieve responses to user commands.
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

    private Regina regina = new Regina();

    // Imaged for the user and Regina Chatbot
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/User.jpg")));
    private final Image reginaImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Regina.jpg")));

    /**
     * Initializes the main window.
     * This method is called after the FXML file is loaded.
     * It binds the scroll pane's vertical value to the dialog container's height
     * and displays a greeting message from the Regina chatbot.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getReginaDialog(regina.greet(), reginaImage)
        );
    }

    /**
     * Injects a new Regina instance into the main window controller.
     *
     * @param r The Regina instance to be set for handling tasks.
     */
    public void setRegina(Regina r) {
        regina = r;
    }

    /**
     * Handles user input when the send button is pressed or when the user presses Enter.
     * Creates two dialog boxes: one for the user input and one for the Regina chatbot's response.
     * Clears the input field after processing the input.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = regina.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getReginaDialog(response, reginaImage)
        );
        // Clear the input field
        userInput.clear();
    }
}

