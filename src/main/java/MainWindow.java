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

    private Luke luke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("images/herrp.jpeg"));
    private Image derpImage = new Image(this.getClass().getResourceAsStream("images/derp.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Shows to user a message when first opening an app.
     */
    public void initialMessage() {
        dialogContainer.getChildren().addAll(
                DialogBox.getLukeDialog(luke.greetUser(), derpImage)
        );
    }

    /** Injects the Duke instance */
    public void setLuke(Luke d) {
        luke = d;
        initialMessage();
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = luke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getLukeDialog(response, derpImage)
        );
        userInput.clear();
    }
}
