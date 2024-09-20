package seedu.avo.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import seedu.avo.Avo;

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
    private Avo avo;
    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image avoImage = new Image(this.getClass().getResourceAsStream("/images/DaAvo.png"));

    /**
     * Initialises the UI with a welcome message
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        String welcomeMessage = "Hi there, I am Avo. Enter 'help' for a list of valid commands.";
        dialogContainer.getChildren().addAll(
                DialogBox.getAvoDialog(welcomeMessage, avoImage)
        );
    }

    /** Injects the Avo instance */
    public void setAvo(Avo a) {
        avo = a;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Avo's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Response response = avo.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getAvoDialog(response.getMessage(), avoImage)
        );
        userInput.clear();
        if (response.getExitStatus()) {
            Platform.exit();
        }
    }
}
