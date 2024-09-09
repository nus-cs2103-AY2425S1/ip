package main.java.angel;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI window of the Angel chatbot application.
 * Manages user interactions and updates the display of dialog boxes.
 */
public class GuiWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Angel angel;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image angelImage = new Image(this.getClass().getResourceAsStream("/images/DaAngel.png"));

    /**
     * Initializes the GUI components.
     * Binds the vertical scroll value of the scroll pane to the height of the dialog container,
     * ensuring that the latest dialog is always visible.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Angel instance to be used by this controller.
     * Allows the GUI to interact with the Angel chatbot backend.
     *
     * @param d The Angel instance to be used.
     */
    public void setAngel(Angel d) {
        angel = d;
    }

    /**
     * Handles user input when the send button is clicked or the enter key is pressed.
     * Creates dialog boxes for the user input and Angel's response, then adds them to the dialog container.
     * Clears the user input field after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = angel.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAngelDialog(response, angelImage)
        );
        userInput.clear();
    }
}
