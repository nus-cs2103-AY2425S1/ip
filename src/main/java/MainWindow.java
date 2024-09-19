import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import krona.Krona;

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

    private Krona krona;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image kronaImage = new Image(this.getClass().getResourceAsStream("/images/Krona.png"));

    /** Injects the Duke instance */
    public void setKrona(Krona k) {
        krona = k;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Call this method after the krona instance is set
      */
    @FXML
    public void displayWelcomeMessage() {
        // Directly display the welcome message without using getCombinedMessage
        dialogContainer.getChildren().add(
                DialogBox.getKronaDialog("Hello! I'm Krona"
                        + "\nWhat can I do for you?\nType \"help\" for a list of available commands.", kronaImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = krona.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getKronaDialog(response, kronaImage)
        );
        userInput.clear();
    }
}
