package ollie.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import ollie.Ollie;

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

    private Ollie ollie;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image ollieImage = new Image(this.getClass().getResourceAsStream("/images/Ollie.png"));

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getOllieDialog(
                Ui.greet(), ollieImage));
    }

    /** Injects the Duke instance */
    public void setOllie(Ollie o) {
        ollie = o;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = ollie.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getOllieDialog(response, ollieImage)
        );
        userInput.clear();
    }

    /**
     * Adds a dialog box with Ollie's response to the dialog container.
     *
     * @param message The message to display.
     * @param sender  The sender of the message (e.g., "User" or "Ollie").
     */
    public void addDialog(String message, String sender) {
        if ("User".equals(sender)) {
            dialogContainer.getChildren().add(DialogBox.getUserDialog(message, userImage));
        } else {
            dialogContainer.getChildren().add(DialogBox.getOllieDialog(message, ollieImage));
        }
    }
}

