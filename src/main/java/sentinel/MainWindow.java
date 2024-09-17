package sentinel;

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

    private Sentinel sentinel;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private final Image sentinelImage = new Image(this.getClass().getResourceAsStream("/images/DaSentinel.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setSentinel(Sentinel d) {
        sentinel = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sentinel.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSentinelDialog(response, sentinelImage)
        );
        userInput.clear();
    }

    /**
     * Outputs a greeting message from Sentinel.
     */
    @FXML
    public void sentinelGreeting() {
        String response = sentinel.greet();;
        dialogContainer.getChildren().addAll(
                DialogBox.getSentinelDialog(response, sentinelImage)
        );
        userInput.clear();
    }
}
