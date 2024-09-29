package optimus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for the main GUI.
 * Adapted from CS2103T JavaFX tutorial -reused
 */
public class MainWindow extends Stage { // Modified to extend Stage

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Optimus optimus;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Optimus instance */
    public void setOptimus(Optimus optimus) {
        this.optimus = optimus;
    }

    //@@author tayxuenye-reused
    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     * Edited by ChatGPT for GUI integration
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        optimus.run(input);
        userInput.clear();
    }
    //@@author

    // Suggested by ChatGPT
    public void showMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getOptimusDialog(message, dukeImage));
    }
}
