package peter;

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

    private Peter peter;

    // ICONS FROM FREEPIK AT "https://www.freepik.com/icons/chatbot" and "https://www.freepik.com/icons/user"
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image peterImage = new Image(this.getClass().getResourceAsStream("/images/chatbot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getPeterDialog("Welcome to Peter! What can I do for you?\n", peterImage));
    }

    /** Injects the Peter instance */
    public void setPeter(Peter peter) {
        this.peter = peter;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = peter.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPeterDialog(response, peterImage)
        );
        userInput.clear();
    }
}