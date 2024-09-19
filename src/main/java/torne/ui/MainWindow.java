package torne.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 * <p></p>
 * Uses FXML instead!
 */
public class MainWindow extends AnchorPane {
    // @FXML annotations allow the member to be accessible to FXML files, regardless of private/protected
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Torne torne;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image torneImage = new Image(this.getClass().getResourceAsStream("/images/torne_transparent_400.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Torne instance then shows the start message for the injected Torne instance.
     */
    public void setTorne(Torne d) {
        torne = d;

        // create greeting dialog
        dialogContainer.getChildren().addAll(
                DialogBox.getTorneDialog(torne.getStartMessage(), torneImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = torne.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getTorneDialog(response, torneImage)
        );
        userInput.clear();
    }
}
