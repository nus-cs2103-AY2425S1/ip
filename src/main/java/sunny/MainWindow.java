package sunny;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

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

    private Ui sunny;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaCatto.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDoggo.png"));
    private Image backgroundImage = new Image(this.getClass().getResourceAsStream("/images/DaBackGround.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Sunny instance */
    public void setSunny(Ui d) {
        sunny = d;
        String welcome = sunny.welcome();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(welcome, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = sunny.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}
