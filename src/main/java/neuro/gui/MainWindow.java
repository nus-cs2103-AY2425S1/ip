package neuro.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import neuro.Neuro;

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

    private Neuro neuro;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Evil_profile.jpg"));
    private Image neuroImage = new Image(this.getClass().getResourceAsStream("/images/Neuro_profile.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Neuro instance */
    public void setNeuro(Neuro n) {
        neuro = n;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Neuro's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        if (input.isEmpty()) {
            return;
        }

        // Remove whitespace
        input = input.trim();

        String response = neuro.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNeuroDialog(response, neuroImage)
        );
        userInput.clear();
    }
}
