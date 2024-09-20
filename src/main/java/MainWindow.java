import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import rudolf.Rudolf;

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

    private Rudolf rudolf;

    private final Image userImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/gingerbreadman.png")));
    private final Image rudolfImage = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/rudolf.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Rudolf instance */
    public void setRudolf(Rudolf d) {
        assert d != null : "Rudolf instance should not be null";
        rudolf = d;
        showGreeting();
    }

    /**
     * Creates a dialog box with Rudolf's greeting when the application first opens.
     */
    private void showGreeting() {
        assert rudolf != null : "Rudolf instance should be initialized before calling showGreeting";
        String greeting = rudolf.getGreeting();
        assert greeting != null && !greeting.isEmpty() : "Rudolf's greeting should not be null or empty";
        dialogContainer.getChildren().add(
                DialogBox.getRudolfDialog(greeting, rudolfImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Rudolf's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null : "User input should not be null";
        assert !input.trim().isEmpty() : "User input should not be empty";

        String response = rudolf.getResponse(input);
        assert response != null : "Rudolf's response should not be null";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRudolfDialog(response, rudolfImage)
        );

        if (input.equals("bye")) {
            System.exit(0);
        }

        userInput.clear();
    }
}

