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
 * This class manages user input and handles the checkbox display for tasks.
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

    private Regina regina;

    // Images for user and Regina Chatbot
    private final Image userImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/User.jpg")));
    private final Image reginaImage = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/Regina.jpg")));

    @FXML
    public void initialize() {
        // Ensure regina is not null before executing functions that involve it
        if (regina != null) {
            scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
            dialogContainer.getChildren().addAll(DialogBox.getReginaDialog(regina.greet(), reginaImage));
        } else {
            System.out.println("Regina instance is not set.");
        }
    }

    public void setRegina(Regina r) {
        regina = r; // Set the Regina instance
        // Ensure to call initialize again if needed or properly manage calls
        if (regina != null) {
            dialogContainer.getChildren().addAll(DialogBox.getReginaDialog(regina.greet(), reginaImage));
        }
    }

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
