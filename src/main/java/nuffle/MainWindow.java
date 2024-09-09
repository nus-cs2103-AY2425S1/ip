package nuffle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import nuffle.ui.Ui;

import java.io.IOException;

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
    private Button sendButton = new Button("Send");
    private Nuffle nuffle;

    private Ui ui = new Ui();
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/robot.jpg"));

    /** Injects the Duke instance */
    public void setDuke(Nuffle n) {
        nuffle = n;
    }

    private void displayGreeting() {
        String greeting = this.ui.printWelcomeMessage(); // Assume this method returns a greeting message
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greeting, dukeImage) // Display greeting from Duke
        );

    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        displayGreeting();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    @FXML
    private void handleUserInput() throws IOException {
        String input = userInput.getText();
        if (!input.trim().isEmpty()) {
            String response = nuffle.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }
        userInput.clear();
    }
}
