package patrick;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import patrick.ui.Ui;

/**
 * Controls the main GUI window in the Patrick application.
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

    private Patrick patrick;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private Image patrickThinkingImage = new Image(this.getClass().getResourceAsStream("/images/patrickThinking.png"));
    private Image angryPatrickImage = new Image(this.getClass().getResourceAsStream("/images/AngryPatrick.png"));

    /**
     * Initializes the MainWindow by binding the scroll pane's vertical value to the height of the dialog container.
     * This ensures that the most recent dialog is always visible.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(Ui.showWelcomeMsg(), dukeImage));
    }

    /**
     * Injects the Patrick instance to be used by this controller.
     *
     * @param pat The Patrick instance to be associated with this controller.
     */
    public void setPatrick(Patrick pat) {
        patrick = pat;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws InterruptedException {
        String input = userInput.getText();
        String response = patrick.getResponse(input);
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
        dialogContainer.getChildren().add(DialogBox
                .getDukeDialog("Hang on Spongebob! Let me check", patrickThinkingImage));
        Image currentPatrick = response.startsWith("Watch your words") ? angryPatrickImage : dukeImage;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(response, currentPatrick));
        userInput.clear();
    }
}
