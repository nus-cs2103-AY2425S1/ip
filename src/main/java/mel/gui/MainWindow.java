package mel.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import mel.main.Mel;

/**
 * Controller for Mel main GUI.
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

    private Mel mel;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));
    private Image melImage = new Image(this.getClass().getResourceAsStream("/images/mel.gif"));
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Mel instance and initializes Mel startup. */
    public void setMel(Mel m) {
        mel = m;
        hello();
    }

    /** Outputs hello message. */
    private void hello() {
        assert melImage != null : "Missing image resource";
        dialogContainer.getChildren().addAll(
                DialogBox.getMelDialog(mel.hello(), melImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Mel's reply and then appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        assert (melImage != null) || (userImage != null) : "Missing image resource";
        String input = userInput.getText();
        String[] response = mel.getResponse(input);
        DialogBox melDialogBox = handleMelDialog(response);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                melDialogBox
        );
        userInput.clear();
    }

    /**
     * Creates dialog box instance based on Mel's response status and string.
     * @param response Mel's response status and string.
     * @return DialogBox Mel response represented in dialog box.
     */
    private DialogBox handleMelDialog(String... response) {
        DialogBox d;
        switch (response[0]) {
        case "exception":
            d = DialogBox.getMelExceptionDialog(response[1], melImage);
            break;
        default:
            d = DialogBox.getMelDialog(response[1], melImage);
        }
        return d;
    }
}
