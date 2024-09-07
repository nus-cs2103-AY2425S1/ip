package bill;

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

    private Bill bill;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image billImage = new Image(this.getClass().getResourceAsStream("/images/DaBill.png"));

    /**
     * Initializes the gui and greets the user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String greetPrompt = "Hello! I'm Bill\n"
                 + "What can I do for you?\n";
        dialogContainer.getChildren().add(DialogBox.getBillDialog(greetPrompt, billImage));

    }

    /** Injects the Bill instance. */
    public void setBill(Bill b) {
        bill = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bill's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bill.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBillDialog(response, billImage)
        );
        userInput.clear();
    }
}
