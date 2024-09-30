package app;

import deez.Deez;
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

    private Deez deez;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/human.png"));
    private Image deezImage = new Image(this.getClass().getResourceAsStream("/images/robot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects Deez and sets up callback
     */
    public void setDeez(Deez d) {
        deez = d;
        deez.initialiseUi((String message) -> outputMessage(message));
    }

    /**
     * Adds a message from Deez in the GUI.
     */
    public void outputMessage(String message) {
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(message, deezImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
            DialogBox.getUserDialog(input, userImage)
        );
        if (!input.isBlank()) {
            deez.handleInput(input);
        }
        userInput.clear();
    }
}
