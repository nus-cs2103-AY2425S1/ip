import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import neon.Neon;

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

    private Neon neon;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg")); //user icon
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/neon.jpg")); //bot image

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void startNeon(Neon neon) {
        this.neon = neon;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(neon.getGreetingLine(), botImage)
        );
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = neon.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, botImage)
        );
        userInput.clear();
    }
}