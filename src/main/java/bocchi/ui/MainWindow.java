package bocchi.ui;

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

    private BocchiWrapper bocchiWrapper;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image bocchiImage = new Image(this.getClass().getResourceAsStream("/images/bocchi.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the BoochiWrapper instance
     */
    public void setBocchiWrapper(BocchiWrapper b) {
        bocchiWrapper = b;
        bocchiWrapper.setMainWindow(this); // creates a bidirectional reference
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bocchiWrapper.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBocchiDialog(response, bocchiImage)
        );
        userInput.clear();
    }

    /**
     * Proactively sends a response from the chatbot.
     * @param response The response from the chatbot.
     */
    public void respond(String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getBocchiDialog(response, bocchiImage)
        );
    }
}
