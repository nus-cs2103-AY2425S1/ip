import charlotte.Charlotte;
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

    private Charlotte charlotte;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image charlotteImage = new Image(this.getClass().getResourceAsStream("/images/Charlotte.png"));

    /**
     * Initializes the MainWindow controller.
     */
    @FXML
    public void initialize() {
        assert dialogContainer != null : "dialogContainer should be initialised";
        assert scrollPane != null : "scrollPane should be initialised";
        assert charlotteImage != null : "charlotteImage should not be null";
        assert userImage != null : "userImage should not be null";

        String welcomeMessage = "Hello! I'm Charlotte!\nWhat can I do for you?";
        dialogContainer.getChildren().add(DialogBox.getCharlotteDialog(welcomeMessage, charlotteImage));
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Charlotte instance */
    public void setCharlotte(Charlotte c) {
        assert c != null : "Charlotte instance should not be null";
        charlotte = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Charlotte's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert userInput != null : "userInput should not be null";
        assert charlotte != null : "Charlotte instance should be initialised before using";

        String input = userInput.getText();
        assert input != null && !input.trim().isEmpty() : "User input should not be null or empty";

        String response = charlotte.getResponse(input);
        assert response != null : "Response from Charlotte should not be null";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCharlotteDialog(response, charlotteImage)
        );
        userInput.clear();
    }
}

