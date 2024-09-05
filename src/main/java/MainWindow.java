import alice.Alice;
import javafx.application.Application;
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

    private Alice alice;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image aliceImage = new Image(this.getClass().getResourceAsStream("/images/Alice.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Alice instance */
    public void setAlice(Alice alice) {
        this.alice = alice;
    }

    /** Greet the user */
    public void getGreeting() {
        String greeting = alice.getGreeting();
        dialogContainer.getChildren().add(DialogBox.getAliceDialog(greeting, aliceImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Alice's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.toLowerCase().startsWith("bye")) {
            Platform.exit();
            System.exit(0);
        }

        String response = alice.getReponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAliceDialog(response, aliceImage)
        );
        userInput.clear();
    }
}
