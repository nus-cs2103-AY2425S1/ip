import javafx.application.Platform;
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

    private Susan susan;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/userpenguin.jpg"));
    private final Image susanImage = new Image(this.getClass().getResourceAsStream("/images/susanpenguin.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Susan instance
     */
    public void setSusan(Susan s) {
        susan = s;
    }

    public void greetUser() {
        String response = susan.getResponse("hello");
        dialogContainer.getChildren().addAll(
                DialogBox.getSusanDialog(response, susanImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Susan's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = susan.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getSusanDialog(response, susanImage)
        );

        // Exit command
        if (response.contains("bye")) {
            Platform.exit();
        }
        userInput.clear();
    }
}
