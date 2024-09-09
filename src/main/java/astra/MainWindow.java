package astra;

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

    private Astra astra;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private final Image astraImage = new Image(this.getClass().getResourceAsStream("/images/Astra.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        // Greet the user
        sendUserMessage("Hello! I'm Astra. How can I help you today?");
    }

    /** Injects the Duke instance */
    public void setAstra(Astra a) {
        astra = a;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = astra.getResponse(input);
        sendUserMessage(input);
        sendAstraMessage(response);
        userInput.clear();
    }

    private void sendUserMessage(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(message, userImage)
        );
    }

    private void sendAstraMessage(String message) {
        dialogContainer.getChildren().add(
                DialogBox.getAstraDialog(message, astraImage)
        );
    }
}
