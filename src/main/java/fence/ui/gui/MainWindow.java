package fence.ui.gui;

import fence.Fence;
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
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Fence fence;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image fenceImage = new Image(this.getClass().getResourceAsStream("/images/Fence.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Fence instance.
     */
    public void setFence(Fence f) {
        fence = f;
    }

    /**
     * Creates a dialog box with Fence's greeting upon GUI launch and appends it to the dialog container.
     */
    public void initializeGreetingDialog() {
        dialogContainer.getChildren().addAll(DialogBox.getFenceDialog(fence.getGreeting(), fenceImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Fence's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert fence != null : "Fence should already be iniitialised";
        String input = userInput.getText();
        String response = fence.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFenceDialog(response, fenceImage)
        );
        userInput.clear();
    }
}
