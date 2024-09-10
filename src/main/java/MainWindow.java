import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pixel.Pixel;

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

    private Pixel pixel;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/c1.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/c2.png"));

    /**
     * Initializes the controller. Binds the scroll pane's vertical value property
     * to the height property of the dialog container.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the Pixel object for the MainWindow.
     *
     * @param p the Pixel object to set
     */
    public void setDuke(Pixel p) {
        pixel = p;
    }

    /**
     * Handles the user input. Creates two dialog boxes, one echoing user input and
     * the other containing Duke's reply, and appends them to the dialog container.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = pixel.getResponse(input);
        dialogContainer.getChildren().addAll(DialogBox.getUserDialog(input, userImage), DialogBox
                .getDukeDialog(response, dukeImage));
        userInput.clear();
    }
}
