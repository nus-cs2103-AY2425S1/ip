package cancelgpt.gui;

import cancelgpt.core.CancelGpt;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for the main GUI.
 */
public class GuiWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private CancelGpt cancelGpt;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image cancelGptImage = new Image(this.getClass().getResourceAsStream("/images/CancelGpt.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the CancelGpt instance
     */
    public void setCancelGpt(CancelGpt d) {
        cancelGpt = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing CancelGpt's reply and
     * then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = cancelGpt.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getCancelGptDialog(response, cancelGptImage)
        );
        userInput.clear();
    }
}
