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

    private Hamyo hamyo;

    private final Image userIcon = new Image(this.getClass().getResourceAsStream("/images/userIcon.png"));
    private final Image hamyoIcon = new Image(this.getClass().getResourceAsStream("/images/hamyoIcon.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Hamyo instance */
    public void setHamyo(Hamyo h) {
        this.hamyo = h;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = this.hamyo.getResponse(input);
        dialogContainer.getChildren().addAll(
            DialogBox.getUserDialog(input, userIcon),
            DialogBox.getDukeDialog(response, hamyoIcon)
        );
        userInput.clear();
    }
}