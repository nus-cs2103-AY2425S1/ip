package michaelscott.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import michaelscott.MichaelScott;
import michaelscott.utils.MichaelScottException;

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

    private MichaelScott michaelScott;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setMichaelScott(MichaelScott d) {
        michaelScott = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = "";
        try {
            input = userInput.getText();
            String response = michaelScott.getResponse(input);
            assert response != null : "response cannot be null";
            String commandType = michaelScott.getCommandType();
            assert commandType != null : "Command Type cannot be null";
            if (response.equals("Exit-Signal")) {
                Platform.exit();
            }
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage, commandType)
            );
        } catch (MichaelScottException e) {
            String response = e.getMessage();
            assert response != null : "Error message cannot be null";
            String commandType = "ErrorOccurred";
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage, commandType)
            );
        } finally {
            userInput.clear();
        }
    }
}

