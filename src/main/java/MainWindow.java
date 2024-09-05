import chatterboxerrors.ChatterBoxError;
import javafx.application.Platform;
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

    private Chatterbox chatterbox;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private final Image chatterboxImage = new Image(this.getClass().getResourceAsStream("/images/DaChatterbox.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Duke instance */
    public void setChatterbox(Chatterbox c) {
        chatterbox = c;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        DialogBox userDialog = DialogBox.getUserDialog(userInput.getText(), userImage);
        String chatterboxResponse;
        try {
            chatterboxResponse = chatterbox.doCommand(userInput.getText());
        } catch (ChatterBoxError e) {
            chatterboxResponse = e.getMessage();
        }
        DialogBox chatterboxDialog = DialogBox.getChatterboxDialog(chatterboxResponse, chatterboxImage);
        dialogContainer.getChildren().addAll(userDialog, chatterboxDialog);
        if (userInput.getText().equals("bye")) {
            Platform.exit();
        }
        userInput.clear();
    }
}
