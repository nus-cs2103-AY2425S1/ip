import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import sinatra.Sinatra;

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

    private Sinatra sinatra;

    private Image martinImage = new Image(this.getClass().getResourceAsStream("/images/Dean.png"));
    private Image sinatraImage = new Image(this.getClass().getResourceAsStream("/images/Sinatra.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Sinatra instance
     */
    public void setSinatra(Sinatra s) {
        sinatra = s;
    }

    /**
     * Shows the hello message from Sinatra
     */
    public void showHelloMessage() {
        String helloMessage = "Why you sneak, I'm Sinatra, whats your fancy?";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(helloMessage,
                        new Image(this.getClass().getResourceAsStream("/images/Sinatra.png")))
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other Sinatra's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sinatra.handleQuery(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, martinImage),
                DialogBox.getDukeDialog(response, sinatraImage)
        );
        userInput.clear();
    }
}
