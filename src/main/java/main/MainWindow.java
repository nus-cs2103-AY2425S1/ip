package main;

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

    private Prince prince;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/king.png"));
    private Image princeImage = new Image(this.getClass().getResourceAsStream("/images/frogprince.png"));

    /**
     * Initialises the scrollPane for the GUI.
     * Displays a welcome message to the user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();
    }

    /** Injects the Prince instance */
    public void setPrince(Prince d) {
        prince = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Prince's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = prince.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPrinceDialog(response, princeImage));
        userInput.clear();
    }

    @FXML
    private void showWelcome() {
        dialogContainer.getChildren().addAll(
                DialogBox.getPrinceDialog("Greetings Master Sir. Prince here, at your service.\n",
                        princeImage));
    }
}
