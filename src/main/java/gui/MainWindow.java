package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import utilities.Bigmouth;
import javafx.application.Platform;


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

    private Bigmouth bigmouth;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/gui/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/gui/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Bigmouth instance */
    public void setBigmouth(Bigmouth b) {
        bigmouth = b;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Bigmouth's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = bigmouth.getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );

        if (response.equals("Goodbye")) {
            Platform.exit();  // This is the correct way to close a JavaFX application
        }

        userInput.clear();
    }
}
