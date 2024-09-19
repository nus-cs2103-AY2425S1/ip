package echo;
import echo.DialogBox;
import echo.Echo;
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

    private Echo echo;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image echoImage = new Image(this.getClass().getResourceAsStream("/images/CuteRobot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void greetUser() {
        dialogContainer.getChildren().addAll(
            DialogBox.getEchoDialog(echo.greetUser(), echoImage)
        );
    }

    /** Injects the Echo instance */
    public void setEcho(Echo e) {
        this.echo = e;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = echo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getEchoDialog(response, echoImage)
        );
        userInput.clear();
    }
}
