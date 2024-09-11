package pikappi;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pikappi.exception.PikappiException;

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

    private Pikappi pikappi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image pikappiImage = new Image(this.getClass().getResourceAsStream("/images/pikappi.png"));

    /**
     * Initializes the main window.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getPikappiDialog("Hello! I'm Pikappi\nWhat can I do for you?\n"
                        + "Type 'help' to see what I can do!", pikappiImage)
        );
    }

    /** Injects the Pikappi instance */
    public void setPikappi(Pikappi d) {
        pikappi = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws PikappiException {
        String input = userInput.getText();
        String response = pikappi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPikappiDialog(response, pikappiImage)
        );
        userInput.clear();
        if (response.equals("Pi-kapi! See you again~\n")) {
            System.exit(0);
        }
    }
}
