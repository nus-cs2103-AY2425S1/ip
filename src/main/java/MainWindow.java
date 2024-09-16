import java.util.Objects;

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
    private TextField input;
    @FXML
    private Button sendButton;

    private Alice alice;

    private Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("UserImage.png")));
    private Image aliceImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("AliceImage.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Alice instance
     */
    public void setAlice(Alice a) {
        alice = a;
    }

    /**
     * Initialises chatbot with welcome message.
     */
    public void start() {
        dialogContainer.getChildren().addAll(DialogBox.getAliceDialog(alice.start(), aliceImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String userInput = input.getText();
        String aliceInput = alice.response(userInput);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput, userImage),
                DialogBox.getAliceDialog(aliceInput, aliceImage)
        );
        input.clear();

        // close the GUI when the user wants to exit
        if (userInput.equals("exit")) {
            Platform.exit();
        }
    }
}
