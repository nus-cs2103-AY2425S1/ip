import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import duke.Duke;

import java.util.Objects;

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

    private Duke duke;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/gingerbreadman.png")));
    private final Image dukeImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/rudolf.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setDuke(Duke d) {
        assert d != null : "Duke instance should not be null";
        duke = d;
        showGreeting();
    }

    /**
     * Creates a dialog box with Duke's greeting when the application first opens.
     */
    private void showGreeting() {
        assert duke != null : "Duke instance should be initialized before calling showGreeting";
        String greeting = duke.getGreeting();
        assert greeting != null && !greeting.isEmpty() : "Duke's greeting should not be null or empty";
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greeting, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "User input should not be null";
        assert !input.trim().isEmpty() : "User input should not be empty";

        String response = duke.getResponse(input);
        assert response != null : "Duke's response should not be null";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}

