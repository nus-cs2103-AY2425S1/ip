package duck.components;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import duck.Duck;

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

    private Duck duck;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image duckImage = new Image(this.getClass().getResourceAsStream("/images/DaDuck.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duck instance and render startup message. */
    public void setDuck(Duck d) {
        this.duck = d;
        // Render the startup message automatically when the Duck instance is set
        displayStartUpMessage();
    }

    /**
     * Displays the startup message from Duck.
     */
    private void displayStartUpMessage() {
        if (duck != null) {
            String startUpMessage = duck.getStartUpMessage();
            dialogContainer.getChildren().add(
                    DialogBox.getDuckDialog(startUpMessage, duckImage)
            );
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duck's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duck.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDuckDialog(response, duckImage)
        );
        userInput.clear();
    }
}
