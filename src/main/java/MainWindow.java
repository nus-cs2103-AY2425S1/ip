import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import winner.Winner;

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

    private Winner winner;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/patrick.png"));
    private Image winnnerImage = new Image(this.getClass().getResourceAsStream("/images/spongebob.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Winner instance */
    public void setWinner(Winner w) {
        winner = w;
        sendHello();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userText = userInput.getText();
        String winnerText = winner.getResponse(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getWinnerDialog(winnerText, winnnerImage));
        userInput.clear();
    }

    private void sendHello() {
        String helloMessage = winner.getHelloMessage();
        dialogContainer.getChildren().add(DialogBox.getWinnerDialog(helloMessage, winnnerImage));
    }
}
