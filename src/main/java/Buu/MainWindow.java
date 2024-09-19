package Buu;

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

    private Buu buu;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/goku.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/buu.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setGPT(Buu buu) {
        this.buu = buu;
        // Get the welcome message and display it in the dialog container
        String welcomeMessage = buu.getResponse(""); // Fetch the welcome message from GPT
        dialogContainer.getChildren().add(
                DialogBox.getBuuDialog(welcomeMessage, dukeImage) // Display GPT's welcome message
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = buu.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBuuDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
