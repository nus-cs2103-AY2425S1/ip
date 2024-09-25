import barry.Barry;
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

    private Barry barry;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/pfp.png"));
    private Image barryImage = new Image(this.getClass().getResourceAsStream("/images/cat.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Barry instance */
    public void setBarry(Barry b) {
        this.barry = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Barry's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {

        String input = userInput.getText();
        String response = this.barry.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDialogBox(response, barryImage));
        userInput.clear();
    }
}
