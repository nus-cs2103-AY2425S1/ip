package assistinator;

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

    private Assistinator assistinator;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/doof.jpg"));
    private Image assistinatorImage = new Image(
            this.getClass().getResourceAsStream("/images/assistinator.png")
    );

    /**
     * Initialises app and shows welcome message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getAssistinatorDialog(
                        "Greetings. How may I assist you with your evil scheming today?",
                        assistinatorImage,
                        false
                )
        );
    }

    /** Injects the Assistinator instance */
    public void setAssistinator(Assistinator a) {
        assistinator = a;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = assistinator.getResponse(input);
        boolean isError = assistinator.isError();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAssistinatorDialog(response, assistinatorImage, isError)
        );
        userInput.clear();
    }

}


